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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v3/p2")
@Slf4j
public class UserController {

	private static final String RequestUrl = "http://part3:8090/api/v3/p3";
    private static final RestTemplate restTemplate = new RestTemplate();
	@PostMapping
	public User save(@RequestBody User user)
	{
		HttpEntity<User> request = new HttpEntity<User>(user);
		ResponseEntity<User> response = restTemplate.exchange(RequestUrl, HttpMethod.POST, request, User.class);
		String responseCode = response.getStatusCode().toString();
		log.info("Response : {} Status code :{}",request.getBody(),responseCode);
		User changedUser = response.getBody();
		changedUser.setId(changedUser.getId()+1);
		return changedUser;
	}
}