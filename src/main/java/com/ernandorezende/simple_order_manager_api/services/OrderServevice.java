package com.ernandorezende.simple_order_manager_api.services;

import com.ernandorezende.simple_order_manager_api.dto.OrderRequest;
import com.ernandorezende.simple_order_manager_api.models.Item;
import com.ernandorezende.simple_order_manager_api.models.Order;
import com.ernandorezende.simple_order_manager_api.models.StockMovement;
import com.ernandorezende.simple_order_manager_api.models.User;
import com.ernandorezende.simple_order_manager_api.repositories.ItemRepository;
import com.ernandorezende.simple_order_manager_api.repositories.OrderRepository;
import com.ernandorezende.simple_order_manager_api.repositories.StockMovementRepository;
import com.ernandorezende.simple_order_manager_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServevice {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final StockMovementRepository stockMovementRepository;
    private final EmailService emailService;


    public Page<Order> findAll(int page, int size){
        return orderRepository.findAll(PageRequest.of(page, size));
    }

    public Order findById(UUID id){
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Order create(OrderRequest orderRequest) {
        Item item = getItemById(orderRequest.itemId());
        User user = getUserById(orderRequest.userId());
        Order order = new Order();
        order.setCreationDate(LocalDateTime.now());
        order.setItem(item);
        order.setUser(user);
        order.setQuantity(orderRequest.quantity());

        Optional<StockMovement> stockMovementOptional = stockMovementRepository.findFirstByItemOrderByCreationDateDesc(order.getItem());
        if(stockMovementOptional.isPresent()) {
            StockMovement lastStockMovement = stockMovementOptional.get();
            log.info("{} items available in stock.", lastStockMovement.getQuantity());
            if(lastStockMovement.getQuantity() > orderRequest.quantity()) {
                StockMovement newStockMovement = new StockMovement();
                newStockMovement.setItem(item);
                newStockMovement.setQuantity(lastStockMovement.getQuantity() - orderRequest.quantity());
                newStockMovement.setCreationDate(LocalDateTime.now());
                lastStockMovement = stockMovementRepository.save(newStockMovement);
                log.info("Item ordered successfully");
                order = orderRepository.save(order);
                String message =  buildEmailMessage(order);
                emailService.sendMessage(user.getEmail(), "Your order status.", message);
                return order;
            } else {
                log.info("Order not fulfilled. The quantity ordered is not available in stock.");
            }
        } else {
            log.info("The item ordered was not found.");
        }
        return null;
    }

    private String buildEmailMessage(Order order) {
        StringBuilder builder = new StringBuilder()
                .append("Thank you for your purchase!\n")
                .append("Your order nº: ")
                .append(order.getId())
                .append(" has been processed successfully.\n")
                .append("We will let you know here any news about this order.");
        return builder.toString();
    }

    @Transactional
    public Order update(OrderRequest orderRequest, UUID id) {
        Order order = findById(id);
        order.setItem(getItemById(orderRequest.itemId()));
        order.setUser(getUserById(orderRequest.userId()));
        order.setQuantity(orderRequest.quantity());
        return orderRepository.save(order);
    }

    @Transactional
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    private Item getItemById(UUID itemId) {
        return itemRepository.findById(itemId).orElseThrow();
    }

    private User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
