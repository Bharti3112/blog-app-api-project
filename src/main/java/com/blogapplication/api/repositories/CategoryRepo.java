package com.blogapplication.api.repositories;

import com.blogapplication.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
}
