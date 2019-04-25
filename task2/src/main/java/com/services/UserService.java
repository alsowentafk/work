package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.User;
import com.repositorys.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public User getbyID(Long id) {
		return userRepository.getById(id);
	}

	public void update(User user) {
		userRepository.update(user);
	}

	public void delete(Long id) {
		userRepository.delete(id);
	}
}
