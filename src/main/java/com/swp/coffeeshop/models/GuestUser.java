package com.swp.coffeeshop.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "guest_user")
public class GuestUser {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tracking_id", nullable = false)
    private String trackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ColumnDefault("2")
    @JoinColumn(name = "role_id")
    private Role role;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}