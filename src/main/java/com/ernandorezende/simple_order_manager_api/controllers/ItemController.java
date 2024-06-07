package com.ernandorezende.simple_order_manager_api.controllers;

import com.ernandorezende.simple_order_manager_api.dto.ItemRequest;
import com.ernandorezende.simple_order_manager_api.models.Item;
import com.ernandorezende.simple_order_manager_api.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<Page<Item>> findAll(
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size) {
        return new ResponseEntity<>(itemService.findAll(page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable UUID id) {
        return new ResponseEntity<>(itemService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody ItemRequest itemRequest) {
        return new ResponseEntity<>(itemService.create(itemRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@RequestBody ItemRequest itemRequest, @PathVariable UUID id) {
        return new ResponseEntity<>(itemService.update(itemRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
