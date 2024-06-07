package com.ernandorezende.simple_order_manager_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_STOCK_MOVEMENT")
public class StockMovement implements Serializable {
    @Serial
    private static final long serialVersionUID = -8204184642524988585L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;

}
