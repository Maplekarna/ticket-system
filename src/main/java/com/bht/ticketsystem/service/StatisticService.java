package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Exception.VersionCollisionException;
import com.bht.ticketsystem.Repository.MovieRepository;
import com.bht.ticketsystem.Repository.StatisticRepository;
import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;


    @Autowired
    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;

    }

    public synchronized List<Statistic> getStatic() {
        return statisticRepository.findAll();
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
