package com.ernandorezende.simple_order_manager_api.services;

import com.ernandorezende.simple_order_manager_api.dto.StockMovementRequest;
import com.ernandorezende.simple_order_manager_api.models.StockMovement;
import com.ernandorezende.simple_order_manager_api.repositories.ItemRepository;
import com.ernandorezende.simple_order_manager_api.repositories.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final ItemRepository itemRepository;

    public Page<StockMovement> findAll(int page, int size) {
        return stockMovementRepository.findAll(PageRequest.of(page, size));
    }

    public StockMovement findById(UUID id){
        return stockMovementRepository.findById(id).orElseThrow();
    }

    @Transactional
    public StockMovement create(StockMovementRequest stockMovementRequest) {
        StockMovement stockMovement = new StockMovement();
        stockMovement.setItem(itemRepository.findById(stockMovementRequest.itemId()).get());
        stockMovement.setQuantity(stockMovementRequest.quantity());
        stockMovement.setCreationDate(LocalDateTime.now());
        return stockMovementRepository.save(stockMovement);
    }

    @Transactional
    public StockMovement update(StockMovementRequest stockMovementRequest, UUID id) {
        StockMovement stockMovement = findById(id);
        stockMovement.setQuantity(stockMovementRequest.quantity());
        return stockMovementRepository.save(stockMovement);
    }

    @Transactional
    public void delete(UUID id) {
        stockMovementRepository.deleteById(id);
    }

}
