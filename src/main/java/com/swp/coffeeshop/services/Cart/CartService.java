package com.swp.coffeeshop.services.Cart;

import com.swp.coffeeshop.models.*;
import com.swp.coffeeshop.repositories.CartRepository;
import com.swp.coffeeshop.repositories.UserRepository;
import com.swp.coffeeshop.services.Product.ProductService;
import com.swp.coffeeshop.services.User.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService implements ICartService {
    CartRepository cartRepository;
    ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public void addProductToCart(User user, Integer productId, Map<String, Object> attributes, Integer quantity) {
        List<Cart> carts = null;
        Cart cart = null;
        Product product = productService.getProduct(productId).get();
        ProductVariant productVariant = null;
        if (!attributes.isEmpty()) {
            carts = getAllCartByUserId(user.getId()).stream()
                    .filter(c -> c.getProductVariant() != null && c.getProductVariant().getAttribute().equals(attributes)).toList();
        } else {
            carts = getAllCartByUserId(user.getId()).stream()
                    .filter(c -> c.getProduct() != null && c.getProduct().getId().equals(productId)).toList();
        }
        if (!carts.isEmpty()) {
            cart = carts.getFirst();
            cart.addQuantity(quantity);
        } else {
            productVariant = productService.getProductVariant(productId, attributes);
            cart = new Cart(user, product, productVariant, quantity);
        }
        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(GuestUser guest, Integer productId, Map<String, Object> attributes, Integer quantity) {
        List<Cart> carts = null;
        Cart cart = null;
        Product product = productService.getProduct(productId).get();
        ProductVariant productVariant = null;
        if (!attributes.isEmpty()) {
            carts = getAllCartByTrackingId(guest.getTrackingId()).stream()
                    .filter(c -> c.getProductVariant() != null && c.getProductVariant().getAttribute().equals(attributes)).toList();
        } else {
            carts = getAllCartByTrackingId(guest.getTrackingId()).stream()
                    .filter(c -> c.getProduct() != null && c.getProduct().getId().equals(productId)).toList();
        }
        if (!carts.isEmpty()) {
            cart = carts.getFirst();
            cart.addQuantity(quantity);
        } else {
            productVariant = productService.getProductVariant(productId, attributes);
            cart = new Cart(guest, product, productVariant, quantity);
        }
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCartByUserId(Integer userId) {
        List<Cart> carts = cartRepository.findAll();
        return cartRepository.findAll().stream()
                .filter(c -> c.getUser() != null && c.getUser().getId().equals(userId))
                .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                .toList();
    }

    @Override
    public List<Cart> getAllCartByTrackingId(String trackingId) {
        return cartRepository.findAll().stream()
                .filter(c -> c.getGuest() != null && c.getGuest().getTrackingId().equals(trackingId))
                .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                .toList();
    }

    @Override
    public void removeCart(Integer id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void removeCart(List<Integer> list) {
        for (Integer id : list) {
            cartRepository.deleteById(id);
        }
    }


    @Override
    public Cart getCartById(Integer id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public void updateQuantity(Integer id, Integer quantity) {
        Cart cart = getCartById(id);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }
}
