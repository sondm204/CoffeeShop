package com.swp.coffeeshop.dto;

public class ProductPrice {
    private int originPrice;
    private int salePrice;
    private Byte status;

    public ProductPrice() {

    }

    public ProductPrice(int originPrice, int salePrice) {
        this.originPrice = originPrice;
        this.salePrice = salePrice;
    }

    public int getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(int originPrice) {
        this.originPrice = originPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
