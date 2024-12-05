package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.services.Product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/CoffeeShop")
public class MenuController {

    ProductService productService;

    public MenuController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "menu";
    }
}
