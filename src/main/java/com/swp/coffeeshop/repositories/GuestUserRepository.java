package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.GuestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestUserRepository extends JpaRepository<GuestUser, Integer> {
}
