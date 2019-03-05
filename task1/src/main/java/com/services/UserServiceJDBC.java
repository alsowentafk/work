package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.models.UserDTO;
import com.repositorys.UserRepositoryJDBC;
import com.transoformers.UserUserODT;

@Service
public class UserServiceJDBC {

	@Autowired
	private UserRepositoryJDBC repository;

	@Autowired
	private UserUserODT converter;

	public UserDTO getUser(Long id) {
		return converter.ConvertToUserODT(repository.findOne(id));
	}

	public void saveUser(UserDTO userODT) {
		repository.save(converter.ConvertToUser(userODT));
	}

	public void deleteUser(Long id) {
		repository.delete(id);
	}

	public void update(UserDTO userODT) {
		repository.update(converter.ConvertToUser(userODT));
	}
}
