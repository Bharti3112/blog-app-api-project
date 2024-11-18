package com.blogapplication.api.payloads;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {


	private int id;

	@NotEmpty
	@Size(min = 4, message = "message should be of min 4 char")
	private String name;

	@Email(message = "Email address is not valid")
	private String  email;

	@NotEmpty
	@Size(min = 3, max =10, message = "password should be between 3 to 10 chars")
	private String  password;

	@NotEmpty
	private String  about;

	private Set<RoleDTO> roles = new HashSet<>();

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public UserDTO() {
	}

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	// Getter and Setter for name
	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	// Getter and Setter for email
	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	// Getter and Setter for password

	@JsonIgnore
	public String getPassword() {
	    return password;
	}

	@JsonProperty
	public void setPassword(String password) {
	    this.password = password;
	}

	// Getter and Setter for about
	public String getAbout() {
	    return about;
	}

	public void setAbout(String about) {
	    this.about = about;
	}
}
