package com.swp.coffeeshop.services.Order;

import com.swp.coffeeshop.models.Order;
import com.swp.coffeeshop.models.OrderItem;
import com.swp.coffeeshop.repositories.OrderItemRepository;
import com.swp.coffeeshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
