package com.example.demo.foundation;

import com.example.demo.Category.Category;
import com.example.demo.Category.CategoryRepository;
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
public class FoundationController {

    @Autowired
    private FoundationRepository foundationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FoundationService foundationService;

    @GetMapping("/addorganisation")
    public String addorganisation(Model model) {
        Foundation foundation = new Foundation();
        model.addAttribute("organisation", foundation);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "addorganisation";
    }

    @PostMapping("/addorganisation")
    public String addorganisation (@Valid Foundation foundation, @RequestParam(name = "category.id") Long id, BindingResult bindingResult) {
       if (bindingResult.hasErrors()) {
            return "addorganisation";
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
}
