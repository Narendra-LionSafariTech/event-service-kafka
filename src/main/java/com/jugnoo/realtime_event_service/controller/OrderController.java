package com.jugnoo.realtime_event_service.controller;

import com.jugnoo.realtime_event_service.dto.CreateOrderRequest;
import com.jugnoo.realtime_event_service.model.OrderEvent;
import com.jugnoo.realtime_event_service.service.producer.EventProducerService;
import com.jugnoo.realtime_event_service.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final EventProducerService producerService;
    private final RedisService redisService;

    /**
     * Create a new order event -> send to Kafka
     */
    @PostMapping("/create")
    public ResponseEntity<CreateOrderRequest> createOrder(@RequestBody OrderEvent orderEvent) {
        producerService.sendOrderEvent(orderEvent);

        CreateOrderRequest response = new CreateOrderRequest(
                "Order event sent to Kafka",
                orderEvent.getOrderId(),
                "success"
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Get latest order status from Redis
     */
    @GetMapping("/{orderId}/status")
    public ResponseEntity<?> getOrderStatus(@PathVariable String orderId) {
        OrderEvent orderEvent = redisService.getOrder(orderId);
        if (orderEvent == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Order not found with ID: " + orderId);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(orderEvent);
    }
}
