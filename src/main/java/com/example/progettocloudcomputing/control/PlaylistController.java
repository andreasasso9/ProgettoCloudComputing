package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.entity.Playlist;
import com.example.progettocloudcomputing.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/playlist")
@AllArgsConstructor
public class PlaylistController {
	private final PlaylistService playlistService;

	@GetMapping("/create")
	public String create(@RequestParam("name") String name, @RequestParam("email") String email) {
		Playlist playlist=new Playlist();

		playlist.setName(name);
		playlist.setEmailProprietario(email);
		playlist.setSongs(new ArrayList<>());

		boolean result=playlistService.save(playlist);

		return "index";
	}

	@GetMapping("/get")
	public List<Playlist> getPlaylistsByEmail(@RequestParam("email") String email) {
		return playlistService.getByEmail(email);
	}
}
