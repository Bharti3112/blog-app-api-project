package com.blogapplication.api.services;

import com.blogapplication.api.entities.Category;
import com.blogapplication.api.exceptions.ResourceNotFoundException;
import com.blogapplication.api.payloads.CategoryDTO;
import com.blogapplication.api.repositories.CategoryRepo;
import jakarta.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo catRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category cat = this.categoryDTOToCategory(categoryDTO);
        Category addedcat = this.catRepo.save(cat);
        return this.categoryToCategoryDTO(addedcat);
    }

    @Override
    public CategoryDTO updateUser(CategoryDTO categoryDTO, Integer categoryId) {
        Category cat = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        cat.setCategoryDescription(categoryDTO.getCategoryDescription());
        cat.setCategoryTitle(categoryDTO.getCategoryTitle());
        Category addedcat = this.catRepo.save(cat);
        return this.categoryToCategoryDTO(addedcat);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        Category cat = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
       return this.categoryToCategoryDTO(cat);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> allCat = this.catRepo.findAll();
        List<CategoryDTO> allCatDto = allCat.stream().map(category -> this.categoryToCategoryDTO(category)).collect(Collectors.toList());
        return  allCatDto;
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        Category cat = this.catRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        this.catRepo.delete(cat);
    }
    public CategoryDTO categoryToCategoryDTO(Category category){
       CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO =  this.modelMapper.map(category, CategoryDTO.class);
       return categoryDTO;
    }
    public Category categoryDTOToCategory( CategoryDTO categoryDTO){
        Category category = new Category();
        category =  this.modelMapper.map(categoryDTO, Category.class);
        return category;
    }
}
