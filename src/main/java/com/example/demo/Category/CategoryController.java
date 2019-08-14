package com.example.demo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/addcategory")
    public String addcategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "addcategory";
    }

    @PostMapping("/addcategory")
    public String addcategory(Category category) {
        categoryRepository.save(category);
        return "redirect:categories";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @GetMapping("/deletecategory/{id}")
    public String deletecategory(@PathVariable Long id) {
        Category category = categoryRepository.getById(id);
        categoryRepository.delete(category);
        return "redirect:/categories";
    }

    @GetMapping("/editcategory/{id}")
    public String editcategory(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryRepository.getById(id));
        return "editcategory";
    }

    @PostMapping("/editcategory")
    public String editcategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

}
