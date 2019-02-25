package com.services;

import com.repositorys.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.UserODT;
import com.transoformers.UserUserODT;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public UserUserODT converter = new UserUserODT();

	public UserODT getUserbyID(long id) {
		return converter.ConvertToUserODT(repository.findOne(id));
	}

	public void save(UserODT userODT) {
		repository.save(converter.ConvertToUser(userODT));
	}

	public void update(UserODT userODT) {
		repository.save(converter.ConvertToUser(userODT));
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}
