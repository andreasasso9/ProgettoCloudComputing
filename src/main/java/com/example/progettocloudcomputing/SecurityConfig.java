package com.example.progettocloudcomputing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
//				.authorizeHttpRequests(request->request.requestMatchers("/", "/.auth/me", "/user/save", "/style/*").permitAll().anyRequest().authenticated())
				.oauth2Login(login->login.loginPage("/login").defaultSuccessUrl("/index", true));

		return http.build();
	}

}
