package com.jugnoo.realtime_event_service.controller;

import com.jugnoo.realtime_event_service.model.OrderEvent;
import com.jugnoo.realtime_event_service.service.producer.EventProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventProducerController {

    private final EventProducerService producerService;

    @PostMapping("/create")
    public String createOrder(@RequestBody OrderEvent orderEvent) {
        producerService.sendOrderEvent(orderEvent);
        return "✅ Order event published: " + orderEvent.getOrderId();
    }
}
