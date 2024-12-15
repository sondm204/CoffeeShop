package com.swp.coffeeshop.dto;

import java.util.List;

public class OrderRequest {
    private List<Integer> cartIds;
    private Integer addressId;
    private Integer totalAmount;
    private String paymentMethod;

    public OrderRequest(List<Integer> cardIds, Integer addressId, Integer totalAmount, String paymentMethod) {
        this.cartIds = cardIds;
        this.addressId = addressId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    public OrderRequest() {
    }

    public List<Integer> getCartIds() {
        return cartIds;
    }

    public void setCartIds(List<Integer> cartIds) {
        this.cartIds = cartIds;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
