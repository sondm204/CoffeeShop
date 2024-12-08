package com.swp.coffeeshop.services.User;

import com.swp.coffeeshop.models.GuestUser;
import com.swp.coffeeshop.models.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findByUsername(String username);

    void saveGuestUser(String trackingId);

    GuestUser getGuestUserByTrackingId(String trackingId);
}
