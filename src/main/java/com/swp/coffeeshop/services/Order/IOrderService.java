package com.swp.coffeeshop.services.Order;

import com.swp.coffeeshop.models.Order;
import com.swp.coffeeshop.models.OrderItem;

public interface IOrderService {
    public void saveOrder(Order order);

    public void saveOrderItem(OrderItem orderItem);
}
