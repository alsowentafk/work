package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.models.UserDTO;
import com.repositorys.UserRepositoryJDBC;
import com.transoformers.UserUserDTO;;

@Service
public class UserServiceJDBC {

	@Autowired
	private UserRepositoryJDBC repository;

	@Autowired
	private UserUserDTO converter;

	public UserDTO getUser(Long id) {
		return converter.ConvertToUserDTO(repository.findOne(id));
	}

	public void saveUser(UserDTO userDTO) {
		repository.save(converter.ConvertToUser(userDTO));
	}

	public void deleteUser(Long id) {
		repository.delete(id);
	}

	public void update(UserDTO userDTO) {
		repository.update(converter.ConvertToUser(userDTO));
	}
}
