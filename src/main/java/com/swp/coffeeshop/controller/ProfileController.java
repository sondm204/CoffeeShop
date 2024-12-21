package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.UserAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/CoffeeShop")
public class ProfileController {

    @PostMapping("/profile/add-address")
    public void addAddress(UserAddress address) {

    }
}
