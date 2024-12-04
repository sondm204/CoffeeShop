package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.ImageGalery;
import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.models.Trend;
import com.swp.coffeeshop.models.User;
import com.swp.coffeeshop.services.Galery.GaleryService;
import com.swp.coffeeshop.services.Product.ProductService;
import com.swp.coffeeshop.services.Trend.TrendService;
import com.swp.coffeeshop.services.User.UserService;
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

    public PageController(UserService userService, ProductService productService, TrendService trendService, GaleryService galeryService) {
        this.userService = userService;
        this.productService = productService;
        this.trendService = trendService;
        this.galeryService = galeryService;
    }


    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {
        Object object = session.getAttribute("username");
        User user = null;
        if (object != null) {
            String username = (String) object;
            user = userService.findByUsername(username);
            session.setAttribute("user", user);
        }
        model.addAttribute("user", user);

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
