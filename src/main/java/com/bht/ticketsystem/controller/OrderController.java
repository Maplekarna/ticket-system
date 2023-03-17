package com.bht.ticketsystem.controller;


import com.bht.ticketsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void reserveSlotService(int showingId, String userId, int count) {
        orderService.reserveSlots(showingId, userId, count);
    }

    public void showOrderHistoryService(String userId) {
        orderService.getOrderHistory(userId);
    }

}
