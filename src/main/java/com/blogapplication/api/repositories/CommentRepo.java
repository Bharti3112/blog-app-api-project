package com.blogapplication.api.repositories;

import com.blogapplication.api.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
