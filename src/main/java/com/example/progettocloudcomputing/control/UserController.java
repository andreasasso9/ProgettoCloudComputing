package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.entity.Song;
import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.SongService;
import com.example.progettocloudcomputing.service.UserService;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final SongService songService;

	@GetMapping("/save")
	public String save() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return String.format("credentials: %s<br>principal: %s<br>name: %s<br>details: %s", auth.getCredentials(), auth.getPrincipal(), auth.getName(), auth.getDetails());
	}

	@GetMapping("/addFavorite")
	public boolean addFavorite(@RequestParam("email") String email, @RequestParam("songId") String songId) {
		boolean response=userService.addFavorite(email, songId);
		return response;
	}

	@GetMapping("/getFavoriteSongs")
	public List<Song> getFavoriteSongs(@RequestParam("email") String email) {
		List<String> favorites=userService.getById(email).getFavoriteSongs();
		List<Song> response=new ArrayList<>();
		favorites.stream().forEach(songId->{
			Song s=songService.getById(songId);
			if (s!=null)
				response.add(s);
		});

		return response;
	}

	@GetMapping("/removeFavorite")
	public boolean removeFavorite(@RequestParam("songId") String songId, @RequestParam("email") String email) {
		User user=userService.getById(email);
		if (user!=null) {
			user.getFavoriteSongs().remove(songId);
			userService.save(user);
			return true;
		}
		return false;
	}
	
}
