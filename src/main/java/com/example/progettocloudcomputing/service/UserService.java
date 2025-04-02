package com.example.progettocloudcomputing.service;

import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;

	public boolean save(User user) {
		userRepository.save(user);
		return true;
	}

	public User getById(String email) {
		return userRepository.findById(email).orElse(null);
	}

	public boolean addFavorite(String email, String id) {
		User user = userRepository.findById(email).orElse(null);
		if (user != null) {
			user.getFavoriteSongs().add(id);
			userRepository.save(user);
			return true;
		}
		return false;
	}
}
