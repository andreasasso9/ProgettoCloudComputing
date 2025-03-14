package com.example.progettocloudcomputing.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.auth/login")
public class ProvaController {

	@GetMapping("/done")
	public String done() {
		return "done";
	}
}
