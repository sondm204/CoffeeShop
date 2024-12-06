package com.swp.coffeeshop.services.Product;

import com.swp.coffeeshop.models.Category;
import com.swp.coffeeshop.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> getAllProducts();

    public Optional<Product> getProduct(int id);

    public List<Category> getAllCategories();
}
