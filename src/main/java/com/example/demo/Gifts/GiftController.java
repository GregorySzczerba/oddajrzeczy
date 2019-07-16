package com.example.demo.Gifts;

import com.example.demo.User.User;
import com.example.demo.User.UserService;
import com.example.demo.foundation.FoundationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
