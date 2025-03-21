package com.example.progettocloudcomputing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.httpBasic(x->x.disable());
//				.authorizeHttpRequests(request->request
//						.requestMatchers("/provalogin", "/login", "/static/**", "/style/**"/*, "/script/**"*//*, "/image/**"*/).permitAll()
//						.requestMatchers("/admin/**").hasRole("ADMIN")
//						.anyRequest().authenticated())
//				.oauth2Login(oauth2->oauth2
//						.loginPage("/provalogin")
//						.successHandler((request, response, authentication) -> {
//							SecurityContextHolder.getContext().setAuthentication(authentication);
//							response.sendRedirect("/index");
//						}))
//				.csrf(csrf->csrf.ignoringRequestMatchers("/admin/**"));


		return http.build();
	}

}
