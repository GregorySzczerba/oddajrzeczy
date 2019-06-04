package com.example.demo.Gifts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GiftController {

    @GetMapping("/form")
    public String addgifts() {
        return "form";
    }
}
