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
	/* private final UserService userService; */

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = {"/", "/index"})
	public String index(HttpServletRequest request, Model model) {
		/* System.out.println("page controller\n\n\n\n\n"); */
		User u=(User) request.getSession().getAttribute("user");
		System.out.println(u+"\n\n\n\n");
		System.out.println(SecurityContextHolder.getContext().getAuthentication()+"\n\n\n\n");

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
