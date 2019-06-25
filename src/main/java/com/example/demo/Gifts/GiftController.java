package com.example.demo.Gifts;

import com.example.demo.Organisation.OrganisationService;
import com.example.demo.User.User;
import com.example.demo.User.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GiftController {

    private GiftsService giftsService;
    private OrganisationService organisationService;
    private UserService userService;
    private TypeOfGiftsService typeOfGiftsService;
    private TypeOfGiftRepository typeOfGiftRepository;
    private GiftsRepository giftsRepository;

    public GiftController(GiftsService giftsService, OrganisationService organisationService, UserService userService, TypeOfGiftsService typeOfGiftsService, TypeOfGiftRepository typeOfGiftRepository,
                          GiftsRepository giftsRepository) {
        this.giftsService = giftsService;
        this.organisationService = organisationService;
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
        model.addAttribute("organisations", organisationService.selectOrganisations());
        return "form";
    }

    @PostMapping("/addgifts")
    public String addGiftsPost(@ModelAttribute Gifts gifts, @RequestParam("id") int userId, @RequestParam("foundationId") Long id) {
        gifts.setOrganisation(organisationService.findById(id));
        gifts.setUser(userService.findUserById(userId));
        System.out.println(gifts);
        giftsRepository.save(gifts);
       return"redirect:userpanel";
    }
}
