package com.jugnoo.realtime_event_service.service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jugnoo.realtime_event_service.dto.OrderRedisDTO;
import com.jugnoo.realtime_event_service.model.OrderEvent;
import com.jugnoo.realtime_event_service.repository.OrderRepository;
import com.jugnoo.realtime_event_service.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventConsumerService {

    private final RedisService redisService;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${redis.order.ttl:3600}") // default TTL 1 hour
    private long redisTtlSeconds;

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @KafkaListener(topics = "order-events", groupId = "order-group")
    public void consumeOrderEvent(String message) {
        try {
            // Deserialize message
            OrderEvent orderEvent = objectMapper.readValue(message, OrderEvent.class);
            log.info(" Consumed Order Event: {}", orderEvent);

            // --- Save to Redis (cache) ---
            OrderRedisDTO redisDTO = new OrderRedisDTO(
                    orderEvent.getOrderId(),
                    orderEvent.getStatus(),
                    orderEvent.getAmount(),
                    LocalDateTime.now().format(formatter)
            );
            redisService.saveOrder(redisDTO, redisTtlSeconds);
            log.info("Stored in Redis with TTL {}s: {}", redisTtlSeconds, redisDTO);

            // --- Save to DB (source of truth) ---
            OrderEvent orderEntity = new OrderEvent(
                    orderEvent.getOrderId(),
                    orderEvent.getStatus(),
                    orderEvent.getAmount(),
                    LocalDateTime.now()
            );
            orderRepository.save(orderEntity);
            log.info("💾 Saved to DB: {}", orderEntity);

        } catch (Exception e) {
            log.error("Failed to process order event: {}", message, e);
            // TODO: Optionally produce to Dead Letter Topic
        }
    }
}
