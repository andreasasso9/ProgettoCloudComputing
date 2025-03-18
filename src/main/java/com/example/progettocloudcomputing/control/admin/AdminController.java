package com.example.progettocloudcomputing.control.admin;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.example.progettocloudcomputing.entity.Song;
import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.SongService;
import com.example.progettocloudcomputing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private final UserService userService;
	private final SongService songService;

	@Value("${STORAGE_ACCOUNT_KEY}")
	private String blobAccountKey;

	@Autowired
	public AdminController(UserService userService, SongService songService) {
		this.userService = userService;
		this.songService = songService;
	}

	@PostMapping("/addSong")
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


		String connectionString=String.format("DefaultEndpointsProtocol=https;AccountName=storageaccountprogetto;AccountKey=%s;EndpointSuffix=core.windows.net", blobAccountKey);

		Song song=new Song();
		song.setName(name);
		song.setSinger(singer);
		System.out.println(connectionString+"\n\n\n\n\n\n\n");

		BlobContainerClient clientContainer=new BlobContainerClientBuilder()
				.connectionString(connectionString)
				.containerName("songs-container")
				.buildClient();

		BlobClient client= clientContainer.getBlobClient(name);
		client.upload(file.getInputStream(), file.getSize(), true);
		song.setSongUrl(client.getBlobUrl());

		songService.save(song);

		return new RedirectView("/index");
	}
}
