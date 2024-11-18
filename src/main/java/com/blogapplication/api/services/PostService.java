package com.blogapplication.api.services;

import com.blogapplication.api.entities.Post;
import com.blogapplication.api.payloads.PostDTO;
import com.blogapplication.api.payloads.PostResponse;

import java.util.List;

public interface PostService {
 PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryID);
 PostDTO updatePost(PostDTO postDTO, Integer id);
 void deletePost(Integer id);
 PostDTO getPostById(Integer id);
 PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
 List<PostDTO> getPostsByUser(Integer userId);
 List<PostDTO> getPostsByCategory(Integer categoryId);
}
