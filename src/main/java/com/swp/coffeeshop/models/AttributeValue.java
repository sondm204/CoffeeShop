package com.swp.coffeeshop.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "attribute_values")
public class AttributeValue {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "attribute_id")
    private ProductAttribute attribute;

    @Column(name = "attribute_value", length = 100)
    private String attributeValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(ProductAttribute attribute) {
        this.attribute = attribute;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

}