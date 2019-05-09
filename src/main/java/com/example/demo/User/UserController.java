package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/userpanel")
    public String userpanel() {
        return "userpanel";
    }
}
