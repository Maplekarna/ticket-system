package com.bht.ticketsystem.service;

import com.bht.ticketsystem.entity.db.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleServiceTest {


    @Autowired
    ScheduleService scheduleService;

    @Test
    void iniSchedule() {
        Integer showingId = 1;
        Integer remaining = 150;
        Integer price = 15;
        String time1 = "2022-10-12 09:20:00";
        String time2 = "2022-10-12 09:30:00";
        String time3 = "2022-10-12 09:40:00";
        String time4 = "2022-10-12 09:50:00";

        scheduleService.addSchedule(showingId, time1, remaining, price);
        scheduleService.addSchedule(showingId, time2, remaining, price);
        scheduleService.addSchedule(showingId, time3, remaining, price);
        scheduleService.addSchedule(showingId, time4, remaining, price);
    }


    @Test
    void checkScheduleList() {

    }
}