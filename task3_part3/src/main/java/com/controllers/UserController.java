package com.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.models.User;

@RestController
@RequestMapping("/api/v3/p3")
public class UserController {
	
	@PostMapping
	public User save(@RequestBody User user)
	{
		user.setContent(user.getContent()+" changed by docker_part3");
		return user;
	}
}