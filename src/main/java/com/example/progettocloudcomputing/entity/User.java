package com.example.progettocloudcomputing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {
	private String id;
	private String email;
	private String name;

	public User(OidcUser user) {
		id = user.getIdToken().getSubject();
		email = user.getEmail();
		name = user.getName();
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
