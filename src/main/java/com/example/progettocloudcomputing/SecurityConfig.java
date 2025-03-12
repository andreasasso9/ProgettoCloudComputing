package com.example.progettocloudcomputing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(Customizer.withDefaults())
				.logout(logout->SecurityContextHolder.clearContext())
				.authorizeHttpRequests(request->request.requestMatchers("/").authenticated().anyRequest().permitAll());
		return http.build();
	}
}
