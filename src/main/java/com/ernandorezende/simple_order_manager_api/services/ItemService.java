package com.ernandorezende.simple_order_manager_api.services;

import com.ernandorezende.simple_order_manager_api.dto.ItemRequest;
import com.ernandorezende.simple_order_manager_api.models.Item;
import com.ernandorezende.simple_order_manager_api.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public Page<Item> findAll(int page, int size){
        return itemRepository.findAll(PageRequest.of(page, size));
    }

    public Item findById(UUID id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Item create(ItemRequest itemRequest) {
        Item item = new Item();
        item.setName(itemRequest.name());
        return itemRepository.save(item);
    }

    @Transactional
    public Item update(ItemRequest itemRequest, UUID id) {
        Item item = findById(id);
        item.setName(itemRequest.name());
        return itemRepository.save(item);
    }

    @Transactional
    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }
}
