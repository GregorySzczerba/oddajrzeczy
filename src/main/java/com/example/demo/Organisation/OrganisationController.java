package com.example.demo.Organisation;

import com.example.demo.Category.Category;
import com.example.demo.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrganisationController {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/addorganisation")
    public String addorganisation(Model model) {
        Organisation organisation = new Organisation();
        model.addAttribute("organisation", organisation);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "addorganisation";
    }

    @PostMapping("/addorganisation")
    public String addorganisation (Organisation organisation) {
       organisationRepository.save(organisation);
       return "redirect:adminpanel";

    }
}
