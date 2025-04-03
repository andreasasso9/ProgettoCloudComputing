package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PageController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = {"/", "/index"})
	public String index(HttpServletRequest request, Model model) {
		User u=(User) request.getSession().getAttribute("user");

		model.addAttribute("user", u);

		return "index";
	}

	@GetMapping("/admin/addSong")
	public String addSong() {
		return "/admin/addSong";
	}

	@GetMapping("/logout")
	public String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "login";
	}
}
