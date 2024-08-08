package org.ere.contest.cfredisservice.web;

import org.ere.contest.cfredisservice.service.OrderService;
import org.ere.contest.orderstarter.model.Order;
import org.ere.contest.orderstarter.model.request.OrderRequest;
import org.ere.contest.orderstarter.web.OrderController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v4/api/order")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest order) {
        var uuid = orderService.createOrder(map(order));
        return ResponseEntity.status(HttpStatus.CREATED).body(uuid);
    }

    @GetMapping("/delete/{orderUuid}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderUuid) {
        orderService.deleteOrder(orderUuid);
        return ResponseEntity.ok().body("deleted");
    }

    @GetMapping("/{orderUuid}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderUuid) {
         return orderService.getOrder(orderUuid)
                 .map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
