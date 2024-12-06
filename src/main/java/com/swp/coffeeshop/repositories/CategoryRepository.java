package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
