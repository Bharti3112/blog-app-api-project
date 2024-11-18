package com.blogapplication.api.services;
import com.blogapplication.api.configs.AppConstants;
import com.blogapplication.api.repositories.RoleRepo;
import com.blogapplication.api.services.UserServices;


import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogapplication.api.entities.*;
import com.blogapplication.api.payloads.*;
import com.blogapplication.api.repositories.UserRepo;
import com.blogapplication.api.exceptions.*;
@Service
public class UserServiceImpl implements UserServices {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO registerNewUser(UserDTO userdto) {
		Users user = this.modelMapper.map(userdto, Users.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		Users newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDTO.class);
	}

	@Override
	public UserDTO createUser(UserDTO userDto) {
		Users user = this.dtoToUser(userDto);
		this.userRepo.save(user);
		UserDTO userDTO = this.UserToDTO(user);
		return null;
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer userID) {
		Users user = this.userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("Users"," Id ", userID));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		this.userRepo.save(user);
		UserDTO savedUser = this.UserToDTO(user);
		return savedUser;
	}

	@Override
	@Transactional
	public UserDTO getUserById(Integer userId) {
		Users user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Users"," Id ", userId));

		UserDTO userdto = this.UserToDTO(user);
		return userdto;
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<Users>  userList = this.userRepo.findAll();
		List<UserDTO>  userdtoList = userList.stream().map(user -> this.UserToDTO(user)).collect(Collectors.toList());
		return userdtoList;
	}
    @Transactional
	@Override
	public void deleteUserById(Integer userId) {
		Users user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Users"," Id ", userId));
		user.setRoles(new HashSet<>());
		this.userRepo.delete(user);
	}
	private Users dtoToUser(UserDTO userdto) {
		Users user = new Users();
//		user.setId(userdto.getId());
//		user.setAbout(userdto.getAbout());
//		user.setPassword(userdto.getPassword());
//		user.setEmail(userdto.getEmail());
//		user.setName(userdto.getName());
		user = this.modelMapper.map(userdto,Users.class);
		return user;
	}
	private UserDTO UserToDTO(Users user) {
		UserDTO userdto = new UserDTO();
//		userdto.setId(user.getId());
//		userdto.setAbout(user.getAbout());
//		userdto.setPassword(user.getPassword());
//		userdto.setEmail(user.getEmail());
//		userdto.setName(user.getName());
		userdto = this.modelMapper.map(user,UserDTO.class);
		return userdto;
	}

}
