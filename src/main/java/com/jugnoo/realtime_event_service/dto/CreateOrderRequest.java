package com.jugnoo.realtime_event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private String message;
    private String orderId;
    private String status;
}
