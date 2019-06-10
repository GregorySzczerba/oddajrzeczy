package com.example.demo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "redirect:adminpanel";
    }

}
