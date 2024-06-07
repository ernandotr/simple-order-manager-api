package com.ernandorezende.simple_order_manager_api.controllers;

import com.ernandorezende.simple_order_manager_api.dto.OrderRequest;
import com.ernandorezende.simple_order_manager_api.models.Order;
import com.ernandorezende.simple_order_manager_api.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderServevice;

    @GetMapping
    public ResponseEntity<Page<Order>> findAll(
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size ) {
        Page<Order> orders = orderServevice.findAll(page, size);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderServevice.findById(id));
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderServevice.create(orderRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Order> update (@RequestBody OrderRequest orderRequest, @PathVariable UUID id) {
        return new ResponseEntity<>(orderServevice.update(orderRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        orderServevice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
