package com.example.progettocloudcomputing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {
	@Id
	private String email;
	private String name;
	private String password;
	private String role;

	@Override
	public String toString() {
		return "User{" +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
