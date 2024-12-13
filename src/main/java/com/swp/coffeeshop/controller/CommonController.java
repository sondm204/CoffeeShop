package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.User;
import com.swp.coffeeshop.services.Cart.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public class CommonController {
    static CartService cartService;

    public CommonController(CartService cartService) {
        CommonController.cartService = cartService;
    }

    public static void updateCartSize(HttpSession session) {
        int cartSize = cartService.getAllCartByUserId(((User) session.getAttribute("user")).getId()).size();
        session.setAttribute("cartSize", cartSize);
    }
}
