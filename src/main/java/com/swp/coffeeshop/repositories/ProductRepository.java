package com.swp.coffeeshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swp.coffeeshop.models.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
