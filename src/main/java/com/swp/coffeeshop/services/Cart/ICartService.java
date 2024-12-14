package com.swp.coffeeshop.services.Cart;

import com.swp.coffeeshop.models.Cart;
import com.swp.coffeeshop.models.GuestUser;
import com.swp.coffeeshop.models.User;

import java.util.List;
import java.util.Map;

public interface ICartService {
    public void addProductToCart(User user, Integer productId, Map<String, Object> attributes, Integer quantity);

    public void addProductToCart(GuestUser guest, Integer productId, Map<String, Object> attributes, Integer quantity);

    public List<Cart> getAllCartByUserId(Integer userId);

    public List<Cart> getAllCartByTrackingId(String trackingId);

    public void removeCart(Integer id);

    public Cart getCartById(Integer id);
}
