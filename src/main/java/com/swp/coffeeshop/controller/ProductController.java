package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.models.ProductVariant;
import com.swp.coffeeshop.services.Product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/CoffeeShop")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") int id, Model model) {
        var product = productService.getProduct(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        List<ProductVariant> productVariants = productService.getAllProductVariants(id);
        Map<String, Set<String>> attributes = new HashMap<>();
        for (ProductVariant productVariant : productVariants) {
            for (String key : productVariant.getAttribute().keySet()) {
                attributes.putIfAbsent(key, new HashSet<>());
                attributes.get(key).add(productVariant.getAttribute().get(key).toString());
            }
        }
        model.addAttribute("attributes", attributes);
        return "product";
    }
}
