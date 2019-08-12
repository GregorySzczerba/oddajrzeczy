package com.example.demo.foundation;

import com.example.demo.Category.Category;
import com.example.demo.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FoundationController {

    @Autowired
    private FoundationRepository foundationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FoundationService foundationService;

    @GetMapping("/addfoundation")
    public String addfoundation(Model model) {
        Foundation foundation = new Foundation();
        model.addAttribute("foundation", foundation);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "addfoundation";
    }

    @PostMapping("/addfoundation")
    public String addfoundation (@Valid Foundation foundation, BindingResult bindingResult, @RequestParam(name = "category.id") Long id) {
       if (bindingResult.hasErrors()) {
            return "redirect:admimnpanel";
       }

       foundation.setCategory(categoryRepository.getById(id));
       foundationRepository.save(foundation);
       return "redirect:adminpanel";
    }

    @GetMapping("/foundations")
    public String organisations(Model model) {
        List<Foundation> foundations = foundationService.selectFoundations();
        model.addAttribute("foundations", foundations);
        return "foundations";
    }

    @GetMapping("/editfoundation/{id}")
    public String editfoundation(@PathVariable Long id, Model model) {
        Foundation foundation = foundationRepository.getById(id);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("foundation", foundation);
        model.addAttribute("categories", categories);
        return "editfoundation";
    }

    @PostMapping("/editfoundation")
    public String editfoundation(Foundation foundation) {
        foundationRepository.save(foundation);
        return "redirect:adminpanel";
    }

    @GetMapping("/deletefoundation/{id}")
    public String deletefoundation(@PathVariable Long id) {
        Foundation foundation = foundationRepository.getById(id);
        foundationRepository.delete(foundation);
        return "redirect:/adminpanel";
    }
}
