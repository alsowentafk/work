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
import com.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserService service;

	@GetMapping("{id}")
	public UserDTO getUserbyID(@PathVariable long id) {
		log.info("get {} at {}", service.getUserbyID(id), Calendar.getInstance().getTime());
		return service.getUserbyID(id);
	}

	@PostMapping
	public void save(@RequestBody UserDTO userDTO) {
		service.save(userDTO);
		log.info("save {} at {}", userDTO, Calendar.getInstance().getTime());
	}

	@PutMapping
	public void update(@RequestBody UserDTO userODT) {
		service.update(userODT);
		log.info("update {} at {}", userODT, Calendar.getInstance().getTime());
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("delete {} at {}", service.getUserbyID(id), Calendar.getInstance().getTime());
		service.delete(id);
	}

}