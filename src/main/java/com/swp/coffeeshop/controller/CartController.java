package com.swp.coffeeshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/CoffeeShop")
public class CartController {

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestBody Map<String, Object> productOrder) {
        for (Map.Entry<String, Object> entry : productOrder.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        
        return ("redirect:/CoffeeShop/product/" + productOrder.get("productId"));
    }
}
