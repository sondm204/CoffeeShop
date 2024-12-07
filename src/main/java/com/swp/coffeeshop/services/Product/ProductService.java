package com.swp.coffeeshop.services.Product;

import com.swp.coffeeshop.models.Category;
import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.models.ProductVariant;
import com.swp.coffeeshop.repositories.CategoryRepository;
import com.swp.coffeeshop.repositories.ProductRepository;
import com.swp.coffeeshop.repositories.VariantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    ProductRepository productRepository;
    CategoryRepository productCategoryRepository;
    VariantRepository variantRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository productCategoryRepository, VariantRepository variantRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.variantRepository = variantRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductVariant> getAllProductVariants(int id) {
        return variantRepository.findAll().stream()
                .filter(v -> v.getProduct().getId() == id)
                .toList();
    }


}
