package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/api/v3")
public class Task3Application {

	@PostMapping("/post")
	public User save()
	{
		User user = new User();
		user.setId(1);
		user.setContent("Hello world Docker");
		return user;
	}
	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
	}

}
