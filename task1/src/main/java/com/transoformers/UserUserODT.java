package com.transoformers;

import com.models.User;
import com.models.UserODT;
import org.springframework.stereotype.Service;;

@Service
public class UserUserODT {

	public User ConvertToUser(UserODT userODT) {
		User user = new User();
		user.setId(userODT.getId());
		user.setContent(userODT.getContent());
		return user;
	}

	public UserODT ConvertToUserODT(User user) {
		UserODT userODT = new UserODT();
		userODT.setId(user.getId());
		userODT.setContent(user.getContent());
		return userODT;
	}
}
