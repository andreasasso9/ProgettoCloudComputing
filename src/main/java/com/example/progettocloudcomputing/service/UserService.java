package com.example.progettocloudcomputing.service;

import com.example.progettocloudcomputing.entity.User;
import com.example.progettocloudcomputing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean save(User user) {
		userRepository.save(user);
		return true;
	}

	public User getById(String email) {
		return userRepository.findById(email).orElse(null);
	}
}
