package com.services;

import com.repositorys.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.User;

@Service
public class UserService {

	@Autowired
	public UserRepository repository;

	public Iterable<User> getAll() {

		return repository.findAll();
	}

	public User getUserbyID(long id) {
		return repository.findOne(id);
	}

	public User save(User us) {
		repository.save(us);
		return us;
	}

	public void update(User user) {
		repository.save(user);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}
