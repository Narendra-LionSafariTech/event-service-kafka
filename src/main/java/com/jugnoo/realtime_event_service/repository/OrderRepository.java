package com.jugnoo.realtime_event_service.repository;

import com.jugnoo.realtime_event_service.model.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEvent, String> {
    // You can add custom queries here if needed
}
