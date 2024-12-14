package com.swp.coffeeshop.services.Address;

import com.swp.coffeeshop.models.UserAddress;

import java.util.List;

public interface IAddressService {
    public List<UserAddress> getAllAddressByUserId(Integer userId);

    public List<UserAddress> getAllAddressByTrackingId(String trackingId);

}
