package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
