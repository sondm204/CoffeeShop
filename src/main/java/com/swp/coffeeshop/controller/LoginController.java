package com.swp.coffeeshop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
@RequestMapping("/CoffeeShop")
public class LoginController {

    @GetMapping("/login")
    public String login(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("username");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        model.addAttribute("currentYear", currentYear);
        return "registration";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }


    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

}
