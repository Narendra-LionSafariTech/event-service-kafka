package com.jugnoo.realtime_event_service.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.jugnoo.realtime_event_service.service.KafkaProducerService;
import com.jugnoo.realtime_event_service.service.RedisService;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final RedisService redisService;
    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/cache")
    public String cacheEvent(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
        return "✅ Event cached in Redis";
    }

    @GetMapping("/cache/{key}")
    public String getCachedEvent(@PathVariable String key) {
        return redisService.get(key);
    }

    @PostMapping("/publish")
    public String publishEvent(@RequestParam String message) {
        kafkaProducerService.sendMessage("realtime-topic", message);
        return "📤 Event published to Kafka";
    }
}
