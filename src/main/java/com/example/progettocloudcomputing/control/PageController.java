package com.example.progettocloudcomputing.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
