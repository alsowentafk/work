package com.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.models.UserDTO;
import com.services.UserServiceJDBC;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class UserControllerJDBC {

	@Autowired
	private UserServiceJDBC service;

	@GetMapping("{id}")
	public UserDTO getUserbyID(@PathVariable long id) {
		log.info("get {} at {}", service.getUser(id), Calendar.getInstance().getTime());
		return service.getUser(id);
	}

	@PostMapping
	public void saveUser(@RequestBody UserDTO userDTO) {
		service.saveUser(userDTO);
		log.info("save {} at {}", userDTO, Calendar.getInstance().getTime());
	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable Long id) {
		log.info("delete {} at {}", service.getUser(id), Calendar.getInstance().getTime());
		service.deleteUser(id);

	}

	@PutMapping
	public void updateUser(@RequestBody UserDTO userDTO) {
		service.update(userDTO);
		log.info("update {} at {}", userDTO, Calendar.getInstance().getTime());
	}
}
