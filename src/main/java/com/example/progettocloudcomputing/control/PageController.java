package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		DefaultOAuth2User oAuth2User= (DefaultOAuth2User) auth.getPrincipal();
		User u=userService.getById(oAuth2User.getAttribute("email"));

		if (u==null) {
			u=new User();
			u.setEmail(oAuth2User.getAttribute("email"));
			u.setName(oAuth2User.getName());
			u.setPassword(oAuth2User.getAttribute("password"));
			u.setRole("USER");

			userService.save(u);
		}

		if (u.getRole().equals("ADMIN")) {
			List<GrantedAuthority> newAuthorities=new ArrayList<>(auth.getAuthorities());
			newAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			List<String> aud=oAuth2User.getAttribute("aud");

			DefaultOAuth2User newOauth2User=new DefaultOAuth2User(newAuthorities, oAuth2User.getAttributes(), "sub");

			OAuth2AuthenticationToken newAuth=new OAuth2AuthenticationToken(newOauth2User, newAuthorities, aud.getFirst());
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}

		model.addAttribute("user", u);

		return "index";
	}

	@GetMapping("/admin/addSong")
	public String addSong() {
		return "/admin/addSong";
	}

	@GetMapping("/provalogin")
	public String provalogin() {
		return "provalogin";
	}
}
