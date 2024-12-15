package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
