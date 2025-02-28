package com.example.progettocloudcomputing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpClient;

@RestController
@RequestMapping("/user")
public class UserController {

//	@GetMapping("/logout")
//	public String logout() throws IOException {
//		System.out.println("ciaooooo");
//
//
//	}
}
