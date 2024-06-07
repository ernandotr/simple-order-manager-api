package com.ernandorezende.simple_order_manager_api.repositories;

import com.ernandorezende.simple_order_manager_api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
