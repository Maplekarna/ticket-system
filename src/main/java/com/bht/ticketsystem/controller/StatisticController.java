package com.bht.ticketsystem.controller;

import com.bht.ticketsystem.entity.db.Statistic;
import com.bht.ticketsystem.service.StatisticService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticController {
    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @ResponseBody
    public List<Statistic> getStatistic(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession(false);
//
//        if (session == null) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            return new ArrayList<>();
//        }

        return statisticService.getStatic();

    }
}
