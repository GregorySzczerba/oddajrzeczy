package com.example.demo.Organisation;

import com.example.demo.Category.Category;
import com.example.demo.Category.CategoryRepository;
import com.example.demo.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrganisationController {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @GetMapping("/addorganisation")
    public String addorganisation(Model model) {
        Organisation organisation = new Organisation();
        model.addAttribute("organisation", organisation);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "addorganisation";
    }

    @PostMapping("/addorganisation")
    public String addorganisation (@Valid Organisation organisation, @RequestParam(name = "category.id") Long id, BindingResult bindingResult) {
       if (bindingResult.hasErrors()) {
            return "addorganisation";
       }

       organisation.setCategory(categoryRepository.getById(id));
       organisationRepository.save(organisation);
       return "redirect:adminpanel";
    }
}
