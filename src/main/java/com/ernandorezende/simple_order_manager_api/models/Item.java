package com.ernandorezende.simple_order_manager_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_ITEM")
public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = -4823703807258748467L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
}
