package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
