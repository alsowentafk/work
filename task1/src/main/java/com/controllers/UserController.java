package com.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.models.User;
import com.services.UserService;;

@RestController
@RequestMapping("/api/v")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/all")
	public Iterable<User> getList() {
		return service.getAll();
	}

	@GetMapping("{id}")
	public User getUserbyID(@PathVariable long id) {
		return service.getUserbyID(id);
	}

	@PostMapping
	public User save(@RequestBody User us) {
		service.save(us);
		return us;
	}

	@PutMapping
	public void update(@RequestBody User user) {
		service.update(user);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}