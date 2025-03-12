package com.example.progettocloudcomputing.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class Admin {

	@RequestMapping("/addSong")
	public void addSong(
			@RequestParam("song_name") String name,
			@RequestParam("artist_name") String singer,
			@RequestParam("audio_file") MultipartFile file
	) {
		System.out.printf("Name: %s, Artist: %s, File: %s%n", name, singer, file);
	}
}
