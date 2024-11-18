package com.blogapplication.api.controllers;

import com.blogapplication.api.payloads.APIREsponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.blogapplication.api.payloads.UserDTO;
import com.blogapplication.api.services.UserServices;

import java.util.List;

@RestController
@RequestMapping("api/users/")
public class UserController {
@Autowired
public UserServices userservice;

@PostMapping("/")
public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
	UserDTO createdDTO = this.userservice.createUser(userDto);
	return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);	
}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> upadteUser(@Valid @RequestBody UserDTO userDto, @PathVariable("userId") Integer id){
		UserDTO updatedDTO = this.userservice.updateUser(userDto,id);
		return ResponseEntity.ok(updatedDTO);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser(){
      return ResponseEntity.ok(this.userservice.getAllUser());
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Integer id){
	return ResponseEntity.ok(this.userservice.getUserById(id));
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<APIREsponse> deleteUser(@PathVariable("userId") Integer id){
		this.userservice.deleteUserById(id);
		return new ResponseEntity<APIREsponse>(new APIREsponse("Deleted SuccessFully", true),HttpStatus.OK);
	}
}
