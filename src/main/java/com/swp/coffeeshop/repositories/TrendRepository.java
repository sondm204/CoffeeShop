package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.Trend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendRepository extends JpaRepository<Trend, Integer> {
}
