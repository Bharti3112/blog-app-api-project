package com.blogapplication.api.repositories;

import com.blogapplication.api.entities.Category;
import com.blogapplication.api.entities.Post;
import com.blogapplication.api.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(Users user);
    List<Post> findByCategory(Category category);
}
