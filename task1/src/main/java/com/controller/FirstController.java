package com.controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.model.User;
import com.Repository.UserRepository;

@RestController
@RequestMapping("/api/v")
public class FirstController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/all")
	public Iterable<User> getList() {
		return userRepository.findAll();
	}

	@GetMapping("{id}")
	public User getUserbyID(@PathVariable long id) {
		return userRepository.findOne(id);
	}

	@PostMapping
	public User addUser(@RequestBody User us) {
		userRepository.save(us);
		return us;
	}

	@PutMapping
	public void update(@RequestBody User user) {
		userRepository.save(user);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		userRepository.delete(id);
	}

}