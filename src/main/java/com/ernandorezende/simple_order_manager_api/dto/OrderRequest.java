package com.ernandorezende.simple_order_manager_api.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public record OrderRequest(UUID itemId,
                           Integer quantity,
                           UUID userId) {
}
