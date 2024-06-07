package com.ernandorezende.simple_order_manager_api.dto;

import java.util.UUID;

public record StockMovementRequest(UUID itemId, Integer quantity) {
}
