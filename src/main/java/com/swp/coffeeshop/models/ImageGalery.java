package com.swp.coffeeshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "image_galeries")
public class ImageGalery {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}