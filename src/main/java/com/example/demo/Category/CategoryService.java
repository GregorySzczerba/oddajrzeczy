package com.example.demo.Category;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.getById(id);
    }
}
