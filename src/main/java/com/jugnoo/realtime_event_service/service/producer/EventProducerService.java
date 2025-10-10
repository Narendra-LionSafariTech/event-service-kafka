package com.jugnoo.realtime_event_service.service.producer;

import com.jugnoo.realtime_event_service.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventProducerService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate; // use OrderEvent type

    private static final String TOPIC = "order-events";

    public void sendOrderEvent(OrderEvent event) {
        kafkaTemplate.send(TOPIC, event.getOrderId(), event);
        System.out.println("Sent Order Event: " + event);
    }
}

