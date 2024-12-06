package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.dto.MenuRequest;
import com.swp.coffeeshop.dto.MenuResponse;
import com.swp.coffeeshop.models.Category;
import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.services.Product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/CoffeeShop")
public class MenuController {

    ProductService productService;
    private final int PAGE_SIZE = 12;

    public MenuController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = productService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("totalPage", (int) Math.ceil((double) products.size() / PAGE_SIZE));
        products = paginateProducts(products, 1);
        model.addAttribute("products", products);
        return "menu";
    }

    @PostMapping("/menu")
    @ResponseBody
    public MenuResponse getMenu(@RequestBody MenuRequest request) {
        MenuResponse menuResponse = new MenuResponse();
        List<String> categoryId = request.getSelectedCategories();
        String orderValue = request.getOrderValue();
        int pageNum = request.getPageNum();
        String searchValue = request.getSearchValue();

        List<Product> products = productService.getAllProducts();

        products = filterProductsByCategory(products, categoryId);
        products = sortProducts(products, orderValue);
        products = searchProducts(products, searchValue);
        int totalNumberProduct = products.size();
        menuResponse.setTotalNumberProduct(totalNumberProduct);
        menuResponse.setTotalPage((int) Math.ceil((double) totalNumberProduct / PAGE_SIZE));
        products = paginateProducts(products, pageNum);
        menuResponse.setProducts(products);
        menuResponse.setPrePage(pageNum);
        return menuResponse;
    }

    public List<Product> filterProductsByCategory(List<Product> products, List<String> selectedCategories) {
        if (selectedCategories.isEmpty()) {
            return products;
        }
        return products.stream()
                .filter(product -> selectedCategories.contains(product.getCategory().getId().toString()))
                .collect(Collectors.toList());
    }

    public List<Product> sortProducts(List<Product> products, String orderValue) {
        if (orderValue.equals("ASC")) {
            return products.stream()
                    .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
                    .collect(Collectors.toList());
        } else if (orderValue.equals("DESC")) {
            return products.stream()
                    .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()))
                    .collect(Collectors.toList());
        } else if (orderValue.equals("new")) {
            return products.stream()
                    .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                    .toList();
        } else if (orderValue.equals("sold")) {
            return products.stream()
                    .sorted((p1, p2) -> p2.getTotalSold().compareTo(p1.getTotalSold()))
                    .toList();
        } else {
            return products;
        }
    }

    public List<Product> searchProducts(List<Product> products, String searchValue) {
        if (searchValue != null) {
            return products.stream()
                    .filter(product -> product.getProductName().toLowerCase().trim().contains(searchValue.toLowerCase().trim()))
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }

    public List<Product> paginateProducts(List<Product> products, int pageNum) {
        int startIndex = (pageNum - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, products.size());
        return products.subList(startIndex, endIndex);
    }


}
