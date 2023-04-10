package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Repository.MovieRepository;
import com.bht.ticketsystem.Repository.StatisticRepository;
import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class StatisticService implements Observer {
    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();

    private final StatisticRepository statisticRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public StatisticService(StatisticRepository statisticRepository, MovieRepository movieRepository) {
        this.statisticRepository = statisticRepository;
        this.movieRepository = movieRepository;
    }

    public synchronized List<Statistic> getStatic() {
        return statisticRepository.findAll();
    }

    @Override
    public synchronized void update(Observable observable, Object o) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Information information = (Information) o;
                updateStatistic(information.getShowingId(), information.getCount());
            }
        };
        executorService.submit(runnable);
    }

    private void updateStatistic(Integer showingId, Integer count) {
        Movie movie = movieRepository.findById(showingId).orElse(null);
        if (movie == null) return;

        Statistic statistic = statisticRepository.findById(showingId).orElse(null);

        if (statistic == null) {
            statistic = new Statistic();
            statistic.setShowingId(showingId).setTicketSold(0).setSales(0).setMovie(movie);
        }
        statistic.ticketsAdd(count);
        statistic.salesAdd(count * movie.getPrice());

        statisticRepository.save(statistic);

    }
}
