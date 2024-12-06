package com.swp.coffeeshop.services.Product;

import com.swp.coffeeshop.models.Category;
import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.repositories.CategoryRepository;
import com.swp.coffeeshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    ProductRepository productRepository;
    CategoryRepository productCategoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return productCategoryRepository.findAll();
    }


}
