package com.cognizant.orderservice.dto;

import com.cognizant.orderservice.model.Order;
import java.util.List;

public class OrderDetailsDto {
    private UserDto user;
    private List<Order> orders;

    public OrderDetailsDto() {}

    public OrderDetailsDto(UserDto user, List<Order> orders) {
        this.user = user;
        this.orders = orders;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
