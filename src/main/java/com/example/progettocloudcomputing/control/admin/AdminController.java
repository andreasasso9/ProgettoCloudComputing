package com.example.progettocloudcomputing.control.admin;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.example.progettocloudcomputing.entity.Song;
import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.SongService;
import com.example.progettocloudcomputing.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
	private final UserService userService;
	private final SongService songService;

	@PostMapping("/addSong")
	public String addSong(
			@RequestParam("song_name") String name,
			@RequestParam("artist_name") String singer,
			@RequestParam("audio_file") MultipartFile file
//			@RequestParam("user") User user
	) throws IOException {
		String email= ((DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttribute("email");
		User user=userService.getById(email);

		if (user == null)
			return "login";

		if (!user.getRole().equals("ADMIN"))
			return "redirect:index";

		Song song=new Song();
		song.setName(name);
		song.setSinger(singer);

		BlobContainerClient clientContainer=new BlobContainerClientBuilder()
				.connectionString()
				.containerName("songs-container")
				.buildClient();

		BlobClient client= clientContainer.getBlobClient(name);
		client.upload(file.getInputStream(), file.getSize(), true);
		song.setSongUrl(client.getBlobUrl());

		songService.save(song);


		return "";
	}
}
