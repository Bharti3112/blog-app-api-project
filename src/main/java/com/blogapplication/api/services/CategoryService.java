package com.blogapplication.api.services;

import com.blogapplication.api.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateUser(CategoryDTO categoryDTO, Integer categoryId);
    CategoryDTO getCategoryById(Integer categoryId);
    List<CategoryDTO> getAllCategory();
    void deleteCategoryById(Integer categoryId);
}
