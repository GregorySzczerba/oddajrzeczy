package com.example.demo.Gifts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView addGiftsPost(Gifts gifts) {
        ModelAndView modelAndView = new ModelAndView();
        /*List<String> giftList = gifts.getTypeOfGift();
        giftList.forEach(System.out::println);
        gifts.setTypeOfGift(giftList);*/
        StringBuilder sb = new StringBuilder();
        for (String gift : gifts.getTypeOfGift()) {
            sb.append(gift);
        };
        gifts.setTypeOfGiftsToString(sb.toString());
       /* gifts.getTypeOfGift().forEach(System.out::println);*/
        giftsRepository.save(gifts);
        modelAndView.setViewName("/index");
        return modelAndView;

    }
}
