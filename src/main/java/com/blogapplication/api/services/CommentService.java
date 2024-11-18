package com.blogapplication.api.services;

import com.blogapplication.api.payloads.CommentDTO;

public interface CommentService {
    public CommentDTO addComment(CommentDTO commentDTO,Integer postId);
    public void deleteComment(Integer commentId);
}
