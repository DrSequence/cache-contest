package org.ere.contest.cfredisservice.service;


import org.ere.contest.orderstarter.model.Order;

import java.util.Optional;

public interface OrderService {
    String createOrder(Order order);
    void deleteOrder(String orderId);
    Optional<Order> getOrder(String orderId);
}
