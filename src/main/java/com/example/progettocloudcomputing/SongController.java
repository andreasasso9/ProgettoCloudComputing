package com.example.progettocloudcomputing;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

	@GetMapping("/get={id}")
	public String get(@PathVariable String id) {
		//todo implementare chiamata a database per ottenere il link alla canzone su blob storage
		return "";
	}

}