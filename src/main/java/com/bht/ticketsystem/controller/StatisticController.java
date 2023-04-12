package com.bht.ticketsystem.controller;

import com.bht.ticketsystem.entity.ResultJSONObject;
import com.bht.ticketsystem.entity.db.Statistic;
import com.bht.ticketsystem.service.StatisticService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatisticController {
    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ResultJSONObject getStatistic(HttpServletRequest request, HttpServletResponse response) {
        return ResultJSONObject.success(statisticService.getStatic());

    }

    @RequestMapping(value = "/statisticsOnPage", method = RequestMethod.GET)
    public ResultJSONObject getStatistic(@RequestParam(value = "page", required = false) Integer page, HttpServletResponse response) {
        return ResultJSONObject.success(statisticService.getStatic(page));

    }
}
