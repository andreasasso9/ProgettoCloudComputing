package com.example.progettocloudcomputing.entity;

import com.google.gson.Gson;
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
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
