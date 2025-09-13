package com.jugnoo.realtime_event_service.controller;

import com.jugnoo.realtime_event_service.model.OrderEvent;
import com.jugnoo.realtime_event_service.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queries/orders")
@RequiredArgsConstructor
public class QueryController {

    private final RedisService redisService;

    @GetMapping("/{orderId}/status")
    public OrderEvent getOrderStatus(@PathVariable String orderId) {
        return redisService.getOrder(orderId);
    }
}
