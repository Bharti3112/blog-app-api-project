package com.blogapplication.api.controllers;

import com.blogapplication.api.payloads.APIREsponse;
import com.blogapplication.api.payloads.CommentDTO;
import com.blogapplication.api.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    public CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
                                                    @PathVariable Integer postId){
       CommentDTO addedComment =  this.commentService.addComment(commentDTO,postId);
        return new ResponseEntity<CommentDTO>(addedComment, HttpStatus.CREATED);
    }
    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<APIREsponse> deleteComment(@PathVariable Integer commentId){
         this.commentService.deleteComment(commentId);
        return new ResponseEntity<APIREsponse>(new APIREsponse("Deleted SuccessFully", true),HttpStatus.OK);
    }
}
