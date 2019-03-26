package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.UserDTO;
import com.repositorys.UserRepository;
import com.transoformers.UserUserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserUserDTO transformer;

	public void save(UserDTO userDTO) {
		userRepository.save(transformer.ConvertToUser(userDTO));
	}

	public UserDTO getbyID(Long id) {
		UserDTO userDTO = transformer.ConvertToUserDTO(userRepository.getbyId(id));
		return userDTO;
	}

	public void update(UserDTO userDTO) {
		userRepository.update(transformer.ConvertToUser(userDTO));
	}

	public void delete(Long id) {
		userRepository.delete(id);
	}
}
