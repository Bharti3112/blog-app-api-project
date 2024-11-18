package com.blogapplication.api.services;

import java.util.List;

import com.blogapplication.api.payloads.UserDTO;

public interface UserServices {
	UserDTO registerNewUser(UserDTO user);
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer userID);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUser();
	void deleteUserById(Integer userId);
}
