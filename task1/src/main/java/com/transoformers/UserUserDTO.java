package com.transoformers;

import com.models.User;
import com.models.UserDTO;
import org.springframework.stereotype.Service;;

@Service
public class UserUserDTO {

	public User ConvertToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setContent(userDTO.getContent());
		return user;
	}

	public UserDTO ConvertToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setContent(user.getContent());
		return userDTO;
	}
}
