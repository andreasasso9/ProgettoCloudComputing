package com.example.progettocloudcomputing.control;

import com.example.progettocloudcomputing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public String save() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return String.format("credentials: %s<br>principal: %s<br>name: %s<br>details: %s", auth.getCredentials(), auth.getPrincipal(), auth.getName(), auth.getDetails());
//		User user = new User();
//		if (user!=null) {
//			User u = new User(user);
//
//		boolean result=userService.save(u);
//		System.out.println(result);
//
//
//			return u.toString();
//		}
//		return "niente";
	}
}
