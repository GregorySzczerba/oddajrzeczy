package com.example.demo.Gifts;

import com.example.demo.User.User;
import com.example.demo.User.UserService;
import com.example.demo.foundation.Foundation;
import com.example.demo.foundation.FoundationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GiftController {

    private GiftsService giftsService;
    private FoundationService foundationService;
    private UserService userService;
    private TypeOfGiftsService typeOfGiftsService;
    private TypeOfGiftRepository typeOfGiftRepository;
    private GiftsRepository giftsRepository;

    public GiftController(GiftsService giftsService, FoundationService foundationService, UserService userService, TypeOfGiftsService typeOfGiftsService, TypeOfGiftRepository typeOfGiftRepository,
                          GiftsRepository giftsRepository) {
        this.giftsService = giftsService;
        this.foundationService = foundationService;
        this.userService = userService;
        this.typeOfGiftsService = typeOfGiftsService;
        this.typeOfGiftRepository = typeOfGiftRepository;
        this.giftsRepository = giftsRepository;
    }

    @GetMapping("/form")
    public String addgifts(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("gifts", new Gifts());
        model.addAttribute("listTypeOfGifts", typeOfGiftRepository.findAll());
        model.addAttribute("foundations", foundationService.selectFoundations());
        return "form";
    }

    @PostMapping("/addgifts")
    public String addGiftsPost(@ModelAttribute Gifts gifts, @RequestParam("id") int userId, @RequestParam("foundationId") Long id) {
        gifts.setFoundation(foundationService.findById(id));
        gifts.setUser(userService.findUserById(userId));
        giftsRepository.save(gifts);
        return "redirect:userpanel";
    }

    @GetMapping("/gifts")
    public String gifts(Model model) {
        List<Gifts> gifts = giftsService.selectGifts();
        System.out.println(gifts);
        model.addAttribute("gifts", gifts);
        return "gifts";
    }

    @GetMapping("/deletegift/{id}")
    public String deletegift(@PathVariable Long id) {
        Gifts gift = giftsRepository.findById(id);
        giftsRepository.delete(gift);
        return "redirect:/userpanel";
    }

    @GetMapping("/editgift/{id}")
    public String editgift(@PathVariable Long id, Model model) {
        Gifts gift = giftsRepository.findById(id);
        User user = gift.getUser();
        List<Foundation> foundations = foundationService.selectFoundations();
        model.addAttribute("foundations", foundations);
        model.addAttribute("user", user);
        model.addAttribute("gifts", gift);
        return "editgift";
    }

    @PostMapping("/editgift")
    public String editgift(Gifts gift, String email, Long foundationId) {
        User user = userService.findUserByEmail(email);
        Foundation foundation = foundationService.findById(foundationId);
        gift.setFoundation(foundation);
        gift.setUser(user);
        giftsRepository.save(gift);
        return "redirect:userpanel";
    }
}
