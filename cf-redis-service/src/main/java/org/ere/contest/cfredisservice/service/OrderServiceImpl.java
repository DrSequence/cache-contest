package org.ere.contest.cfredisservice.service;

import org.ere.contest.orderstarter.model.Order;
import org.ere.contest.orderstarter.model.entity.OrderEntity;
import org.ere.contest.orderstarter.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public String createOrder(Order order) {
        var saved = orderRepository.saveAndFlush(map(order));
        return Objects.requireNonNull(saved).getUuid().toString();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "orders")
    public void deleteOrder(String orderId) {
        var orderUuid = UUID.fromString(orderId);
        orderRepository.deleteById(orderUuid);

        logger.info("Order with id {} has been deleted", orderId);
    }

    @Override
    @Cacheable(cacheNames = "orders")
    public Optional<Order> getOrder(String orderId) {
        return orderRepository
                .findById(UUID.fromString(orderId))
                .map(this::map);
    }

    private OrderEntity map(Order order) {
        var uuid = order.uuid() == null ? UUID.randomUUID() : UUID.fromString(order.uuid());
        return new OrderEntity(
                uuid,
                order.username(),
                order.timestamp()
        );
    }

    private Order map(OrderEntity entity) {
        return new Order(
                entity.getUuid().toString(),
                entity.getUsername(),
                entity.getTimestamp()
        );
    }
}
