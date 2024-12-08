package com.swp.coffeeshop.controller;

import com.sun.tools.jconsole.JConsoleContext;
import com.swp.coffeeshop.dto.ProductPrice;
import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.models.ProductVariant;
import com.swp.coffeeshop.services.Product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/product/{id}/get-price")
    @ResponseBody
    public ProductPrice getPrice(@PathVariable("id") int id, @RequestBody Map<String, Object> attributeJson) {
        var variants = productService.getAllProductVariants(id).stream()
                .filter(v -> v.getAttribute().equals(attributeJson))
                .toList();
        ProductPrice productPrice = new ProductPrice();
        if (variants.isEmpty()) {
            productPrice.setStatus((byte) 0);
        } else {
            productPrice.setSalePrice(variants.getFirst().getSalePrice());
            if (variants.getFirst().getOriginPrice() != null) {
                productPrice.setOriginPrice(variants.getFirst().getOriginPrice());
            }
            productPrice.setStatus((byte) 1);
        }
        return productPrice;
    }
}
