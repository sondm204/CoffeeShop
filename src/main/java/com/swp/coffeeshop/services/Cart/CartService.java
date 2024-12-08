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
        Product product = productService.getProduct(productId).get();
        ProductVariant productVariant = null;
        if (!attributes.isEmpty())
            productVariant = productService.getProductVariant(productId, attributes);
        Cart cart = new Cart(user, product, productVariant, quantity);
        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(GuestUser guest, Integer productId, Map<String, Object> attributes, Integer quantity) {
        Product product = productService.getProduct(productId).get();
        ProductVariant productVariant = null;
        if (!attributes.isEmpty())
            productVariant = productService.getProductVariant(productId, attributes);
        Cart cart = new Cart(guest, product, productVariant, quantity);
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCartByUserId(Integer userId) {
        return List.of();
    }
}
