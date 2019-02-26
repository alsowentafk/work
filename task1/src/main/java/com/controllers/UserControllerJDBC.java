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
import com.models.UserODT;
import com.services.UserServiceJDBC;

@RestController
@RequestMapping("/api/v2")
public class UserControllerJDBC {

	@Autowired
	private UserServiceJDBC service;

	@GetMapping("{id}")
	public UserODT getUserbyID(@PathVariable long id) {
		return service.getUser(id);
	}

	@PostMapping
	public void saveUser(@RequestBody UserODT userODT) {
		service.saveUser(userODT);
	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
	}

	@PutMapping
	public void updateUser(@RequestBody UserODT userODT) {
		service.update(userODT);
	}
}