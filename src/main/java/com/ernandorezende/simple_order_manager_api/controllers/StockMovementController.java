package com.ernandorezende.simple_order_manager_api.controllers;

import com.ernandorezende.simple_order_manager_api.dto.StockMovementRequest;
import com.ernandorezende.simple_order_manager_api.dto.UserRequest;
import com.ernandorezende.simple_order_manager_api.models.StockMovement;
import com.ernandorezende.simple_order_manager_api.models.User;
import com.ernandorezende.simple_order_manager_api.services.StockMovementService;
import com.ernandorezende.simple_order_manager_api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/stock-movements")
public class StockMovementController {
    private final StockMovementService stockMovementService;

    @GetMapping
    public ResponseEntity<Page<StockMovement>> findAll(
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size ) {
        Page<StockMovement> users = stockMovementService.findAll(page, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovement> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(stockMovementService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StockMovement> create(@RequestBody StockMovementRequest  stockMovementRequest) {
        return new ResponseEntity<>(stockMovementService.create(stockMovementRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<StockMovement> update (@RequestBody StockMovementRequest  stockMovementRequest, @PathVariable UUID id) {
        return new ResponseEntity<>(stockMovementService.update(stockMovementRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        stockMovementService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
