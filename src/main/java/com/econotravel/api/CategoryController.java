package com.econotravel.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")


public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}/experiences")
    public List<Experience> getExperiencesofCategoryId(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).get();
        return category.getExperiences();
    }

}
