package com.swp.coffeeshop.services.User;

import com.swp.coffeeshop.models.GuestUser;
import com.swp.coffeeshop.models.User;
import com.swp.coffeeshop.repositories.GuestUserRepository;
import com.swp.coffeeshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    UserRepository userRepository;
    GuestUserRepository guestUserRepository;

    public UserService(UserRepository userRepository, GuestUserRepository guestUserRepository) {
        this.userRepository = userRepository;
        this.guestUserRepository = guestUserRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveGuestUser(String trackingId) {
        guestUserRepository.save(new GuestUser(trackingId));
    }

    @Override
    public GuestUser getGuestUserByTrackingId(String trackingId) {
        return guestUserRepository.findAll().stream().filter(g -> g.getTrackingId().equals(trackingId)).findFirst().orElse(null);
    }
}
