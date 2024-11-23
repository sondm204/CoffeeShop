package com.swp.coffeeshop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_address")
public class UserAddress {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tracking_id", referencedColumnName = "tracking_id")
    private GuestUser tracking;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "isDefault")
    private Byte isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GuestUser getTracking() {
        return tracking;
    }

    public void setTracking(GuestUser tracking) {
        this.tracking = tracking;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

}