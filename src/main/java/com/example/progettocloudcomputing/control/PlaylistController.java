package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.entity.Playlist;
import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.PlaylistService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/playlist")
@AllArgsConstructor
public class PlaylistController {
	private final PlaylistService playlistService;

	@GetMapping("/create")
	public String create(@RequestParam("name") String name, @RequestParam("user") String user, Model model) {
		Gson gson = new Gson();
		User u=gson.fromJson(user, User.class);

		Playlist playlist=new Playlist();

		playlist.setName(name);
		playlist.setEmailProprietario(u.getEmail());
		playlist.setSongs(new ArrayList<>());

		boolean result=playlistService.save(playlist);
		System.out.println("playlist create: "+result);

		model.addAttribute("user", u);

		return "index";
	}

	@GetMapping("/get")
	public List<Playlist> getPlaylistsByEmail(@RequestParam("email") String email) {
		return playlistService.getByEmail(email);
	}
}
