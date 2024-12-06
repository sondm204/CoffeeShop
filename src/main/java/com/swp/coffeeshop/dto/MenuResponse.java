package com.swp.coffeeshop.dto;

import com.swp.coffeeshop.models.Product;

import java.util.List;

public class MenuResponse {
    private List<Product> products;
    private int totalPage;
    private int prePage;
    private int totalNumberProduct;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalNumberProduct() {
        return totalNumberProduct;
    }

    public void setTotalNumberProduct(int totalNumberProduct) {
        this.totalNumberProduct = totalNumberProduct;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }
}
