package com.repositorys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.models.User;

@Repository
public class UserRepository {
	private static Map<Long, User> userRepositoryTemplate = new HashMap<Long, User>();

	public void save(User user) {
		userRepositoryTemplate.put(user.getId(), user);
	}

	public User getbyId(Long id) {
		User user = userRepositoryTemplate.get(id);
		return user;
	}

	public void update(User user) {
		userRepositoryTemplate.put(user.getId(), user);
	}

	public void delete(Long id) {
		userRepositoryTemplate.remove(id);
	}
}
