package com.swp.coffeeshop.services.Product;

import com.swp.coffeeshop.models.Product;
import com.swp.coffeeshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }
}
