package com.bht.ticketsystem.service;


import com.bht.ticketsystem.Repository.StatisticRepository;

import com.bht.ticketsystem.entity.db.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;


    @Autowired
    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;

    }

    public synchronized List<Statistic> getStatic() {
        return getStatic(0);
    }

    public synchronized List<Statistic> getStatic(Integer page) {
        Pageable pageable = PageRequest.of(page, 5);

        Slice<Statistic> statisticSlice = statisticRepository.findAll(pageable);

        return statisticSlice.getContent();

    }

}
