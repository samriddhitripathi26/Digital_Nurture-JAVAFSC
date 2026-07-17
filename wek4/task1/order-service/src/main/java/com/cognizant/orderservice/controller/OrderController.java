package com.cognizant.orderservice.controller;

import com.cognizant.orderservice.dto.OrderDetailsDto;
import com.cognizant.orderservice.dto.UserDto;
import com.cognizant.orderservice.model.Order;
import com.cognizant.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        // Optional: verify that the user exists first using WebClient
        try {
            UserDto user = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/users/" + order.getUserId())
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
            
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to verify user from User Service. Details: " + e.getMessage());
        }

        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderRepository.findByUserId(userId));
    }

    @GetMapping("/user/{userId}/details")
    public ResponseEntity<?> getOrderDetailsByUserId(@PathVariable Long userId) {
        UserDto user;
        try {
            user = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/users/" + userId)
                    .retrieve()
                    .onStatus(status -> status.isError(), clientResponse -> Mono.empty())
                    .bodyToMono(UserDto.class)
                    .block();
        } catch (Exception e) {
            user = new UserDto(userId, "Unknown User (User Service unavailable)", "");
        }

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        List<Order> orders = orderRepository.findByUserId(userId);
        OrderDetailsDto details = new OrderDetailsDto(user, orders);
        return ResponseEntity.ok(details);
    }
}
