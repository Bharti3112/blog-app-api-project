package com.blogapplication.api.controllers;

import com.blogapplication.api.payloads.APIREsponse;
import com.blogapplication.api.payloads.CategoryDTO;
import com.blogapplication.api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    private ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO createdCategory = this.categoryService.createCategory(categoryDTO);
        try {
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
    }
    @PutMapping("/{userId}")
    private ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer userId){
        CategoryDTO updatedCategory = this.categoryService.updateUser(categoryDTO,userId);
        return  ResponseEntity.ok(updatedCategory);
    }
    @GetMapping("/{userId}")
    private ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer userId){
        CategoryDTO categoryDTO = this.categoryService.getCategoryById(userId);
        return ResponseEntity.ok(categoryDTO);
    }
    @GetMapping("/")
    private ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> categoryDTOList = this.categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDTOList);
    }
    @DeleteMapping("/{userId}")
    private ResponseEntity<APIREsponse> deleteCategory(@PathVariable Integer userId){
        this.categoryService.deleteCategoryById(userId);
        return new ResponseEntity<APIREsponse>(new APIREsponse("Deleted SuccessFully", true),HttpStatus.OK);
    }
}
