package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.Cart;
import com.swp.coffeeshop.models.GuestUser;
import com.swp.coffeeshop.models.User;
import com.swp.coffeeshop.models.UserAddress;
import com.swp.coffeeshop.services.Address.AddressService;
import com.swp.coffeeshop.services.Cart.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/CoffeeShop/")
public class CheckoutController {

    CartService cartService;
    AddressService addressService;

    public CheckoutController(CartService cartService, AddressService addressService) {
        this.cartService = cartService;
        this.addressService = addressService;
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam("listCartId") List<Integer> listCartId, HttpSession session, Model model) {
        List<Cart> listCart = new ArrayList<>();
        for (Integer cartId : listCartId) {
            Cart cart = cartService.getCartById(cartId);
            listCart.add(cart);
        }
        model.addAttribute("listCart", listCart);

        Object object = session.getAttribute("user");
        List<UserAddress> listAddress = null;
        UserAddress defaultAddress = null;

        if (object != null) {
            User user = (User) object;
            listAddress = addressService.getAllAddressByUserId(user.getId());
        } else {
            GuestUser guest = (GuestUser) session.getAttribute("guest");
            listAddress = addressService.getAllAddressByTrackingId(guest.getTrackingId());
        }
        if (listAddress != null) {
            defaultAddress = listAddress.stream().filter(a -> a.getIsDefault() == 1).findFirst().orElse(null);
            model.addAttribute("listAddress", listAddress);
            model.addAttribute("defaultAddress", defaultAddress);
        }
        return "checkout";
    }
}
