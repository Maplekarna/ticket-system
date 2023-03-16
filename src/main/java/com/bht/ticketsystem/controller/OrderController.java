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

    public void reserveSlotService() {
        orderService.reserveSlots();
    }

    public void getOrderHistoryService(String userId) {
        orderService.getOrderHistory(userId);
    }

}
