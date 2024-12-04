package com.swp.coffeeshop.services.User;

import com.swp.coffeeshop.models.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findByUsername(String username);
}
