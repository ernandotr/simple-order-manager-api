package com.ernandorezende.simple_order_manager_api.repositories;

import com.ernandorezende.simple_order_manager_api.models.Item;
import com.ernandorezende.simple_order_manager_api.models.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {
    Optional<StockMovement> findFirstByItemOrderByCreationDateDesc(Item item);
}
