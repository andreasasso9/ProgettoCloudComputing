package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/save")
	public String save(@AuthenticationPrincipal OidcUser user) {
		User u=new User(user);

		boolean result=userService.save(u);
		System.out.println(result);

		return u.toString();
	}
}
