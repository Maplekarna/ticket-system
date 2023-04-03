package com.bht.ticketsystem.controller;


import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.ResultJSONObject;

import com.bht.ticketsystem.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(value = "/makeOrder", method = RequestMethod.POST)
    public ResultJSONObject reserveSlotService(@RequestBody @Validated Information information, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("user_id");
        orderService.reserveSlots(userId, information);
        return ResultJSONObject.success(information);


    }

    @RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
    public ResultJSONObject showOrderHistoryService(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("user_id");
        return ResultJSONObject.success(orderService.getOrderHistory(userId));

    }

}
