package com.transoformers;

import com.models.User;
import com.models.UserDTO;
import org.springframework.stereotype.Service;;

@Service
public class UserUserODT {

	public User ConvertToUser(UserDTO userODT) {
		User user = new User();
		user.setId(userODT.getId());
		user.setContent(userODT.getContent());
		return user;
	}

	public UserDTO ConvertToUserODT(User user) {
		UserDTO userODT = new UserDTO();
		userODT.setId(user.getId());
		userODT.setContent(user.getContent());
		return userODT;
	}
}
