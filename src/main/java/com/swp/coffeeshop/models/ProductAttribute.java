package com.swp.coffeeshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_attributes")
public class ProductAttribute {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "product_attributes_name", length = 100)
    private String productAttributesName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductAttributesName() {
        return productAttributesName;
    }

    public void setProductAttributesName(String productAttributesName) {
        this.productAttributesName = productAttributesName;
    }

}