package com.controllers;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.models.UserDTO;
import com.services.UserService;

@RestController
@RequestMapping("/api/v3")
public class Controller {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public void save(@RequestBody UserDTO userDTO)
	{
		userService.save(userDTO);
	}
	
	@GetMapping("{id}")
	public UserDTO getbyId(@PathVariable Long id)
	{
		return userService.getbyID(id);
	}
	
	@PutMapping
	public void update(@RequestBody UserDTO userDTO)
	{
		userService.update(userDTO);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id)
	{
		userService.delete(id);
	}
}
