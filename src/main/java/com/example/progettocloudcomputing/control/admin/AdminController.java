package com.example.progettocloudcomputing.control.admin;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.example.progettocloudcomputing.entity.Song;
import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.SongService;
import com.example.progettocloudcomputing.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Base64;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
	private final UserService userService;
	private final SongService songService;

	@Value("${STORAGE_ACCOUNT_KEY}")
	private String blobAccountKey;
 

	public AdminController(UserService userService, SongService songService) {
		this.userService = userService;
		this.songService = songService;
	}

	@PostMapping("/saveSong")
	public RedirectView addSong(
			@RequestParam("song_name") String name,
			@RequestParam("artist_name") String singer,
			@RequestParam("audio_file") MultipartFile file
	) throws IOException {
		String email= ((DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttribute("email");
		User user=userService.getById(email);

		if (user == null)
			return new RedirectView("/login");

		if (!user.getRole().equals("ADMIN"))
			return new RedirectView("/index");


		Song song=new Song();
		song.setName(name);
		song.setSinger(singer);

		String connectionString=String.format("DefaultEndpointsProtocol=https;AccountName=storageaccountprogetto;AccountKey=%s;EndpointSuffix=core.windows.net", blobAccountKey);

		BlobContainerClient clientContainer=new BlobContainerClientBuilder()
				.connectionString(connectionString)
				.containerName("songs-container")
				.buildClient();

		BlobHttpHeaders headers=new BlobHttpHeaders().setContentType("audio/mpeg").setContentMd5(calculateMD5(file.getInputStream()));
				

		BlobClient client= clientContainer.getBlobClient(name);
		
		client.upload(file.getInputStream(), file.getSize(), true);
		client.setHttpHeaders(headers);
		
		song.setSongUrl(client.getBlobUrl());

		songService.save(song);

		return new RedirectView("/index");
	}

	private byte[] calculateMD5(InputStream inputStream) throws IOException {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[8192];
			int bytesRead;

			// Legge il flusso di byte e aggiorna l'hash
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				md5.update(buffer, 0, bytesRead);
			}

			// Restituisce l'MD5 come stringa Base64
			byte[] md5Bytes = md5.digest();
			return Base64.getEncoder().encode(md5Bytes);
		} catch (Exception e) {
			throw new IOException("Errore nel calcolare MD5", e);
		}
	}
}
