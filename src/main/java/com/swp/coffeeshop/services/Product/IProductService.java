package com.swp.coffeeshop.services.Product;

import com.swp.coffeeshop.models.Category;
import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.models.ProductVariant;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> getAllProducts();

    public Optional<Product> getProduct(int id);

    public List<Category> getAllCategories();

    public List<ProductVariant> getAllProductVariants(int id);
}
