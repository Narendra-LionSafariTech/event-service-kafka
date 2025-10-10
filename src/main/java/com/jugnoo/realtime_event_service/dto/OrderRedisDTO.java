package com.jugnoo.realtime_event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRedisDTO {
    private String orderId;
    private String status;
    private double amount;
    private String createdAt; // ISO String
}

