package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.entity.Song;
import com.example.progettocloudcomputing.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/song")
@AllArgsConstructor
public class SongController {
	private final SongService songService;

	@GetMapping("/get")
	public List<Song> get(@RequestParam("name") String name) {
		return songService.getByName(name);
	}
}