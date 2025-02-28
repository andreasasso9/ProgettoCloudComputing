package com.example.progettocloudcomputing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		System.out.println("ciaoooo");
//
//		http.authorizeHttpRequests(requests -> requests
//				.anyRequest().authenticated())
//				.formLogin(login->login.loginPage("/login"));
//
//		return http.build();
//	}

//	@Bean
//	@RequestScope
//	public ServletUriComponentsBuilder urlBuilder() {
//		return ServletUriComponentsBuilder.fromCurrentRequest();
//	}
}