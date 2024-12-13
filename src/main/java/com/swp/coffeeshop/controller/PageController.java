package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.*;
import com.swp.coffeeshop.services.Cart.CartService;
import com.swp.coffeeshop.services.Galery.GaleryService;
import com.swp.coffeeshop.services.Product.ProductService;
import com.swp.coffeeshop.services.Trend.TrendService;
import com.swp.coffeeshop.services.User.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/CoffeeShop")
public class PageController {

    public final UserService userService;
    public final ProductService productService;
    public final TrendService trendService;
    public final GaleryService galeryService;
    public final CartService cartService;

    public PageController(UserService userService, ProductService productService, TrendService trendService, GaleryService galeryService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.trendService = trendService;
        this.galeryService = galeryService;
        this.cartService = cartService;
    }


    @GetMapping("/home")
    public String showHome(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {

        CommonController.updateCartSize(session);

        Trend trend = trendService.getTrendActive();
        model.addAttribute("trend", trend);

        List<Product> bestSellingProducts = productService.getAllProducts().stream()
                .sorted(Comparator.comparing(Product::getTotalSold).reversed())
                .limit(4)
                .collect(Collectors.toList());
        model.addAttribute("bestSellingProducts", bestSellingProducts);

        List<ImageGalery> galeries = galeryService.getAllGaleries();
        model.addAttribute("galeries", galeries);

        return "home";
    }


}
