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
import com.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("{id}")
	public UserODT getUserbyID(@PathVariable long id) {
		return service.getUserbyID(id);
	}

	@PostMapping
	public void save(@RequestBody UserODT userODT) {
		service.save(userODT);
	}

	@PutMapping
	public void update(@RequestBody UserODT userODT) {
		service.update(userODT);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}