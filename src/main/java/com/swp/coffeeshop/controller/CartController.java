package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.GuestUser;
import com.swp.coffeeshop.models.User;
import com.swp.coffeeshop.services.Cart.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/CoffeeShop")
public class CartController {
    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestBody Map<String, Object> productOrder, HttpServletRequest request) {
        var productId = Integer.parseInt(productOrder.get("productId").toString());
        Map<String, Object> attributes = (Map<String, Object>) productOrder.get("attributes");
        var quantity = Integer.parseInt(productOrder.get("quantity").toString());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        GuestUser guest = (GuestUser) session.getAttribute("guest");
        if (user != null) {
            cartService.addProductToCart(user, productId, attributes, quantity);
        } else if (guest != null) {
            cartService.addProductToCart(guest, productId, attributes, quantity);
        } else {
            return "redirect:/CoffeeShop/login";
        }

        return ("redirect:/CoffeeShop/product/" + productOrder.get("productId"));
    }
}
