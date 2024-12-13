package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.GuestUser;
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
        Object object = session.getAttribute("user");
        int cartSize = 0;
        if (object != null) {
            User user = (User) object;
            cartSize = cartService.getAllCartByUserId(user.getId()).size();
        } else {
            GuestUser guest = (GuestUser) session.getAttribute("guest");
            cartSize = cartService.getAllCartByTrackingId(guest.getTrackingId()).size();
        }
        session.setAttribute("cartSize", cartSize);
    }
}
