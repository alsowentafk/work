package com.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.models.UserDTO;
import com.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService service;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("{id}")
	public UserDTO getUserbyID(@PathVariable long id) {
		logger.info("Hello world {} {}!",service.getUserbyID(id),Calendar.getInstance().getTime());
		return service.getUserbyID(id);
	}

	@PostMapping
	public void save(@RequestBody UserDTO userODT) {
		service.save(userODT);
	}

	@PutMapping
	public void update(@RequestBody UserDTO userODT) {
		service.update(userODT);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}