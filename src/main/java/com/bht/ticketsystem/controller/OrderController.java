package com.bht.ticketsystem.controller;


import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Order;
import com.bht.ticketsystem.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public void reserveSlotService(@RequestBody @Validated Information information, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return ;
        }

        String userId = (String) session.getAttribute("user_id");
        orderService.reserveSlots(userId, information);
    }

    @RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
    @ResponseBody
    public Set<Order> showOrderHistoryService(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new HashSet<>();
        }

        String userId = (String) session.getAttribute("user_id");
        return orderService.getOrderHistory(userId);
    }

}
