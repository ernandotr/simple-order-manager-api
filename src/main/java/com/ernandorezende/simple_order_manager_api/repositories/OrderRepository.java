package com.ernandorezende.simple_order_manager_api.repositories;

import com.ernandorezende.simple_order_manager_api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
