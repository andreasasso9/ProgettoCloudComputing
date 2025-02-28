package com.example.progettocloudcomputing;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('APPROLE_Admin')")
public class AdminCntroller {

	@GetMapping("/addSong")
	public String addSong() {
		return "Add Song";
	}
}
