package com.blogapplication.api.services;

import com.blogapplication.api.entities.Comment;
import com.blogapplication.api.entities.Post;
import com.blogapplication.api.exceptions.ResourceNotFoundException;
import com.blogapplication.api.payloads.CommentDTO;
import com.blogapplication.api.repositories.CommentRepo;
import com.blogapplication.api.repositories.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl implements CommentService{

    @Autowired
    public PostRepo postRepo;
    @Autowired
    public CommentRepo commentRepo;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public CommentDTO addComment(CommentDTO commentDTO,Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," Id ", postId));
        Comment comment =  this.modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
      Comment savedComment =   this.commentRepo.save(comment);
      return this.modelMapper.map(savedComment,CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = this.commentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment"," Id ", id));
      this.commentRepo.delete(comment);
    }
}
