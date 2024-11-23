package com.swp.coffeeshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CoffeeShop")
public class PageController {

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

}
