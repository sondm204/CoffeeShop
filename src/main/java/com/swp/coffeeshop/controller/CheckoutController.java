package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.dto.OrderRequest;
import com.swp.coffeeshop.dto.VNPayResponse;
import com.swp.coffeeshop.models.*;
import com.swp.coffeeshop.services.Address.AddressService;
import com.swp.coffeeshop.services.Cart.CartService;
import com.swp.coffeeshop.services.Order.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/CoffeeShop")
public class CheckoutController {

    CartService cartService;
    AddressService addressService;
    OrderService orderService;
    List<Integer> listCartId;
    Order newOrder;


    public CheckoutController(CartService cartService, AddressService addressService, OrderService orderService) {
        this.cartService = cartService;
        this.addressService = addressService;
        this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public String showCheckout(@RequestParam("listCartId") List<Integer> listCartId, HttpSession session, Model model) {
        List<Cart> listCart = new ArrayList<>();
        for (Integer cartId : listCartId) {
            Cart cart = cartService.getCartById(cartId);
            listCart.add(cart);
        }
        model.addAttribute("listCart", listCart);

        Object object = session.getAttribute("user");
        List<UserAddress> listAddress = null;
        UserAddress defaultAddress = null;

        if (object != null) {
            User user = (User) object;
            listAddress = addressService.getAllAddressByUserId(user.getId());
        } else {
            GuestUser guest = (GuestUser) session.getAttribute("guest");
            listAddress = addressService.getAllAddressByTrackingId(guest.getTrackingId());
        }
        if (listAddress != null) {
            defaultAddress = listAddress.stream().filter(a -> a.getIsDefault() == 1).findFirst().orElse(null);
            model.addAttribute("listAddress", listAddress);
            model.addAttribute("defaultAddress", defaultAddress);
        }
        return "checkout";
    }

    @PostMapping("/checkout/check")
    @ResponseBody
    public String checkout(@RequestBody OrderRequest orderRequest, HttpSession session, Model model) {
        listCartId = orderRequest.getCartIds();
        UserAddress address = addressService.getAddressById(orderRequest.getAddressId());
        Integer totalAmount = orderRequest.getTotalAmount();
        String paymentMethod = orderRequest.getPaymentMethod();

        newOrder = new Order(address, totalAmount, paymentMethod);
        Object object = session.getAttribute("user");
        if (object != null) {
            User user = (User) object;
            newOrder.setUser(user);
        } else {
            GuestUser guest = (GuestUser) session.getAttribute("guest");
            newOrder.setGuest(guest);
        }

        if (paymentMethod.equals("2")) {
            return "/CoffeeShop/vnpay/pay?amount=" + totalAmount;
        } else {
            return "/CoffeeShop/checkout/success";
        }
    }

    @GetMapping("/checkout/success")
    public String showCheckoutSuccess(@RequestParam Map<String, String> params, Model model) {
        orderService.saveOrder(newOrder);
        for (Integer cartId : listCartId) {
            Cart cart = cartService.getCartById(cartId);
            OrderItem orderItem = new OrderItem(newOrder, cart.getProduct(), cart.getQuantity(), cart.getTotalPrice());
            if (cart.getProductVariant() != null) orderItem.setProductVariant(cart.getProductVariant());
            orderService.saveOrderItem(orderItem);
        }
        cartService.removeCart(listCartId);
        if (!params.isEmpty()) {
            VNPayResponse response = new VNPayResponse(params);
            model.addAttribute("response", response);
            return "payment-success";
        }
        return "order-success";
    }
}
