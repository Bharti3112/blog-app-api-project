package com.blogapplication.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.api.entities.Users;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {
	Optional<Users> findByEmail(String email);
}
