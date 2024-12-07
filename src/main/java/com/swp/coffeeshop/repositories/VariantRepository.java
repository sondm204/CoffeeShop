package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<ProductVariant, Integer> {
}
