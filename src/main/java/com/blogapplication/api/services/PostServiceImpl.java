package com.blogapplication.api.services;

import com.blogapplication.api.entities.Category;
import com.blogapplication.api.entities.Post;
import com.blogapplication.api.entities.Users;
import com.blogapplication.api.exceptions.ResourceNotFoundException;
import com.blogapplication.api.payloads.CategoryDTO;
import com.blogapplication.api.payloads.PostDTO;
import com.blogapplication.api.payloads.PostResponse;
import com.blogapplication.api.repositories.CategoryRepo;
import com.blogapplication.api.repositories.PostRepo;
import com.blogapplication.api.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDTO createPost(PostDTO postDTO,Integer userId, Integer categoryId) {
        Users user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Users"," Id ", userId));
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImageName("Default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategoryId(cat);
       Post addedpost = this.postRepo.save(post);
       return this.modelMapper.map(addedpost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "postid", id));
       post.setTitle(postDTO.getTitle());
       post.setContent(postDTO.getContent());
       post.setImageName(postDTO.getImageName());
       Post addedPost = this.postRepo.save(post);
       return this.modelMapper.map(addedPost,PostDTO.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post"," Id ", id));
       this.postRepo.delete(post);
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post"," Id ", id));
        PostDTO postDTO =  this.modelMapper.map(post,PostDTO.class);
        return postDTO;
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
           sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPost = pagePost.getContent();
        List<PostDTO> listOfPostDto = allPost.stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(listOfPostDto);
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        return postResponse;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        Users user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Users"," Id ", userId));
        List<Post> listpost = this.postRepo.findByUser(user);
        List<PostDTO> listOfPostDto = listpost.stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return listOfPostDto;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        List<Post> listpost = this.postRepo.findByCategory(cat);
        List<PostDTO> listOfPostDto = listpost.stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return listOfPostDto;
    }
}
