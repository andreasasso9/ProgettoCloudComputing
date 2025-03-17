package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PageController {
	private final UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = {"/", "/index"})
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User u=new User();

		if (authentication instanceof DefaultOAuth2User) {
			DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

			u.setEmail(user.getAttribute("email"));
			u.setName(user.getAttribute("name"));

			userService.save(u);
		} else {
			u.setEmail("anonymous");
			u.setName("anonymous");
		}

		model.addAttribute("user", u);

		return "index";
	}
}
