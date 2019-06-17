package com.example.demo.Gifts;

import com.example.demo.Organisation.OrganisationService;
import com.example.demo.User.User;
import com.example.demo.User.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GiftController {

    private GiftsService giftsService;
    private OrganisationService organisationService;
    private UserService userService;
    private TypeOfGiftsService typeOfGiftsService;
    private TypeOfGiftRepository typeOfGiftRepository;
    private GiftsRepository giftsRepository;

    public GiftController(GiftsService giftsService, OrganisationService organisationService, UserService userService, TypeOfGiftsService typeOfGiftsService, TypeOfGiftRepository typeOfGiftRepository) {
        this.giftsService = giftsService;
        this.organisationService = organisationService;
        this.userService = userService;
        this.typeOfGiftsService = typeOfGiftsService;
        this.typeOfGiftRepository = typeOfGiftRepository;
    }

    @GetMapping("/form")
    public ModelAndView addgifts(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("gifts", new Gifts());
        modelAndView.addObject("listTypeOfGifts", typeOfGiftRepository.findAll());
        System.out.println(typeOfGiftRepository.findAll());
        modelAndView.addObject("organisations", organisationService.selectOrganisations());
        modelAndView.setViewName("form");
        return modelAndView;
    }

    @PostMapping("/addgifts")
    public String addGiftsPost(@ModelAttribute Gifts gifts, @RequestParam("foundationId") Long id, User user) {
        gifts.setUser(userService.findUserById(user.getId()));
        gifts.setOrganisation(organisationService.findById(id));
        giftsRepository.save(gifts);
        return"form2";
    }
}
