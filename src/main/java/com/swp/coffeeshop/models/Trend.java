package com.swp.coffeeshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trends")
public class Trend {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "sub_title")
    private String subTitle;

    @ColumnDefault("1")
    @Column(name = "active")
    private Byte active;

    @OneToMany(mappedBy = "trend", fetch = FetchType.LAZY)
    private List<TrendProduct> trendProducts = new ArrayList<TrendProduct>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public List<TrendProduct> getTrendProducts() {
        return trendProducts;
    }

    public void addTrendProducts(TrendProduct trendProduct) {
        trendProduct.setTrend(this);
        this.trendProducts.add(trendProduct);
    }

}