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


//    @Override
//    @Transactional
//    public synchronized void update(Observable observable, Object o) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Information information = (Information) o;
//                updateStatistic(information.getShowingId(), information.getCount());
//            }
//        };
//        executorService.submit(runnable);
//    }
//
//    private void updateStatistic(Integer showingId, Integer count) {
//        Movie movie = movieRepository.findById(showingId).orElse(null);
//
//        if (movie != null) {
//            Statistic statistic = statisticRepository.findById(showingId).orElse(null);
//            if (statistic != null) {
//                statistic.setTicketSold(statistic.getTicketsSold() + count);
//                statistic.setSales(statistic.getSales() + count * movie.getPrice());
//
//                statisticRepository.save(statistic);
//            }
//        }
//
//
//    }

}
