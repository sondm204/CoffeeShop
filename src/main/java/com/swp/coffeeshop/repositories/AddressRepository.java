package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<UserAddress, Integer> {
}
