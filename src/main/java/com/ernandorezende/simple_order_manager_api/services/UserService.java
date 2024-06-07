package com.ernandorezende.simple_order_manager_api.services;

import com.ernandorezende.simple_order_manager_api.dto.UserRequest;
import com.ernandorezende.simple_order_manager_api.models.User;
import com.ernandorezende.simple_order_manager_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Page<User> findAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public User create(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.email());
        user.setName(userRequest.name());
        return userRepository.save(user);
    }

    @Transactional
    public User update(UserRequest userRequest, UUID id) {
        User user = findById(id);
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        return userRepository.save(user);
    }

    @Transactional
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
