package com.blogapplication.api.payloads;

import com.blogapplication.api.entities.Category;
import com.blogapplication.api.entities.Comment;
import com.blogapplication.api.entities.Users;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDTO {

    private Integer id;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private Users user;
    private Category category;
    private Set<CommentDTO> comments = new HashSet<>();

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public PostDTO() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
