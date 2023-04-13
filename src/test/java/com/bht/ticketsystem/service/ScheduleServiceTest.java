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
        Integer showingId1 = 1;
        Integer remaining1 = 150;
        Integer price1 = 15;
        String time1 = "2022-10-12 09:20:00";
        String time2 = "2022-10-12 09:30:00";
        String time3 = "2022-10-12 09:40:00";
        String time4 = "2022-10-12 09:50:00";

        scheduleService.addSchedule(showingId1, time1, remaining1, price1);
        scheduleService.addSchedule(showingId1, time2, remaining1, price1);
        scheduleService.addSchedule(showingId1, time3, remaining1, price1);
        scheduleService.addSchedule(showingId1, time4, remaining1, price1);

        Integer showingId2 = 2;
        Integer remaining2 = 120;
        Integer price2 = 25;

        scheduleService.addSchedule(showingId2, time1, remaining2, price2);
        scheduleService.addSchedule(showingId2, time2, remaining2, price2);
        scheduleService.addSchedule(showingId2, time3, remaining2, price2);
        scheduleService.addSchedule(showingId2, time4, remaining2, price2);


        Integer showingId3 = 3;
        Integer remaining3 = 200;
        Integer price3 = 20;

        scheduleService.addSchedule(showingId3, time1, remaining3, price3);
        scheduleService.addSchedule(showingId3, time2, remaining3, price3);
        scheduleService.addSchedule(showingId3, time3, remaining3, price3);
        scheduleService.addSchedule(showingId3, time4, remaining3, price3);


    }


}