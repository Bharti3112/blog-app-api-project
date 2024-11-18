package com.blogapplication.api.controllers;

import com.blogapplication.api.payloads.APIREsponse;
import com.blogapplication.api.payloads.PostDTO;
import com.blogapplication.api.payloads.PostResponse;
import com.blogapplication.api.services.FileService;
import com.blogapplication.api.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
      PostDTO createdpost = postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(createdpost, HttpStatus.CREATED);
    }
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = "10",required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir){
        PostResponse allPostByUser = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
       return ResponseEntity.ok(allPostByUser);
    }
    @GetMapping("/user/{userId}/posts")
   public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
        List<PostDTO> allPostByUser = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDTO>>(allPostByUser,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDTO> allPostBycategory = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(allPostBycategory,HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable Integer postId){
        PostDTO post = this.postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(post,HttpStatus.OK);
    }
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<APIREsponse> deletPost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<APIREsponse>(new APIREsponse("Deleted SuccessFully", true),HttpStatus.OK);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Integer postId){
        PostDTO updatedPost = postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
    }
    @GetMapping("/post/upload/image/{id}")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam MultipartFile image,
                                               @PathVariable Integer id) throws IOException {
        PostDTO postDTO = this.postService.getPostById(id);
        String fileName = this.fileService.uploadImage(path,image);
        postDTO.setImageName(fileName);
        PostDTO updatedPost = this.postService.updatePost(postDTO,id);
        return  new ResponseEntity<PostDTO>(updatedPost,HttpStatus.OK);
    }
    @GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName,
                              HttpServletResponse response) throws IOException {
        InputStream is = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is,response.getOutputStream());
    }
}
