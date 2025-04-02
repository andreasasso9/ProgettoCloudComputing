package com.example.progettocloudcomputing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

	private UserService userService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(request->request
						.requestMatchers("/login", "/static/**", "/style/**", "/user/login").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.oauth2Login(oauth2->oauth2
						.loginPage("/login")
						.successHandler((request, response, authentication) -> loginSuccessHandler(request, response, authentication)))
				.csrf(csrf->csrf.ignoringRequestMatchers("/admin/**", "/playlist/**"))
				.logout(logout-> { logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login")
						.clearAuthentication(true);
					SecurityContextHolder.clearContext();
				});

		return http.build();
	}

	private void loginSuccessHandler(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {
		DefaultOAuth2User oAuth2User = (DefaultOAuth2User) auth.getPrincipal();
		String email=oAuth2User.getAttribute("email")!=null?oAuth2User.getAttribute("email"):oAuth2User.getAttribute("preferred_username");
		User u = userService.getById(email);

		if (u == null) {
			u = new User();
			u.setEmail(email);
			u.setName(oAuth2User.getName());
			u.setRole("USER");

			userService.save(u);
		}

		if (u.getRole().equals("ADMIN")) {
			List<GrantedAuthority> newAuthorities = new ArrayList<>(auth.getAuthorities());
			newAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			List<String> aud = oAuth2User.getAttribute("aud");

			if (aud != null && !aud.isEmpty()) {
				DefaultOAuth2User newOauth2User = new DefaultOAuth2User(newAuthorities, oAuth2User.getAttributes(), "sub");

				OAuth2AuthenticationToken newAuth = new OAuth2AuthenticationToken(newOauth2User, newAuthorities, aud.getFirst());
				SecurityContextHolder.getContext().setAuthentication(newAuth);
			}
		} else
			SecurityContextHolder.getContext().setAuthentication(auth);
		request.getSession().setAttribute("user", u);
		response.sendRedirect("/index");
	}

}
