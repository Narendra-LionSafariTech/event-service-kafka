package com.jugnoo.realtime_event_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent implements Serializable {
    private String orderId;
    private String status;   // e.g., CREATED, CONFIRMED, SHIPPED
    private double amount;
    private LocalDateTime createdAt;
}