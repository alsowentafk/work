package com.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.models.User;

@RestController
@RequestMapping("/api/v3/p1")
public class UserController {

    private static final String RequestUrl = "http://part2:8080/api/v3/p2";
    private static final RestTemplate restTemplate = new RestTemplate();
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user)
	{
		HttpEntity<User> request = new HttpEntity<User>(user);
		ResponseEntity<User> response = restTemplate.exchange(RequestUrl, HttpMethod.POST, request, User.class);
		return response;
	}
}