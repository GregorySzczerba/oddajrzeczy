package com.example.demo.Gifts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GiftController {

    @Autowired
    private GiftsRepository giftsRepository;

    @GetMapping("/form")
    public ModelAndView addgifts() {
        ModelAndView modelAndView = new ModelAndView();
        Gifts gifts = new Gifts();
        modelAndView.addObject("gifts", gifts);
        modelAndView.setViewName("form");
        return modelAndView;
    }

    @PostMapping("/addgifts")
    public String addGiftsPost(Gifts gifts, Model model) {
        giftsRepository.save(gifts);
        model.addAttribute("gifts", gifts);
        return"redirect:/form2/" + gifts.getId();
    }

    @GetMapping("/form2/{id}")
    public ModelAndView addgifts2(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        Gifts gifts = giftsRepository.findOne(id);
        modelAndView.addObject("gifts", gifts);
        modelAndView.setViewName("form2");
        return modelAndView;
    }

    @PostMapping("/addgifts2")
    public String addGiftsPost2(Gifts gifts, Model model) {
        giftsRepository.save(gifts);
        model.addAttribute("gifts", gifts);
        return"redirect:/form3/" + gifts.getId();


    }

    @GetMapping("/form3/{id}")
    public ModelAndView addgifts3() {
        ModelAndView modelAndView = new ModelAndView();
        Gifts gifts = new Gifts();
        modelAndView.addObject("gifts", gifts);
        modelAndView.setViewName("form3");
        return modelAndView;
    }
}
