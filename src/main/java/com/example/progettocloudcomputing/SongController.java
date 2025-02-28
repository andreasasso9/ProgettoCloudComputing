package com.example.progettocloudcomputing;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

	@GetMapping("/get={id}")
	public String get(@PathVariable String id, Model model) {
		//todo implementare chiamata a database per ottenere il link alla canzone su blob storage
		return "";
	}

}