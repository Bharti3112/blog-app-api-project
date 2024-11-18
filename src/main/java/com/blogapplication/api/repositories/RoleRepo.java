package com.blogapplication.api.repositories;

import com.blogapplication.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
