package com.bht.ticketsystem.service;

import com.bht.ticketsystem.dao.StatisticDao;
import com.bht.ticketsystem.entity.db.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    private final StatisticDao statisticDao;

    @Autowired
    public StatisticService(StatisticDao statisticDao) {
        this.statisticDao = statisticDao;
    }

    public synchronized List<Statistic> getStatic() {
        return statisticDao.getStatistics();
    }
}
