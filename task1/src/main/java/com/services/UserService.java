package com.services;

import com.repositorys.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.UserDTO;
import com.transoformers.UserUserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserUserDTO converter;

	public UserDTO getUserbyID(long id) {
		return converter.ConvertToUserDTO(repository.findOne(id));
	}

	public void save(UserDTO userODT) {
		repository.save(converter.ConvertToUser(userODT));
	}

	public void update(UserDTO userODT) {
		repository.save(converter.ConvertToUser(userODT));
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}
