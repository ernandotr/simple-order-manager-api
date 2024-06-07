package com.ernandorezende.simple_order_manager_api.repositories;

import com.ernandorezende.simple_order_manager_api.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
