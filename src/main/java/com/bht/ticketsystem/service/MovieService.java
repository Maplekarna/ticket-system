package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Repository.MovieRepository;
import com.bht.ticketsystem.Repository.ScheduleRepository;
import com.bht.ticketsystem.Repository.StatisticRepository;
import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Schedule;

import com.bht.ticketsystem.entity.db.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MovieService implements Observer {

    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();


    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;

    private final StatisticRepository statisticRepository;


    @Autowired
    public MovieService(MovieRepository movieRepository, ScheduleRepository scheduleRepository, StatisticRepository statisticRepository) {
        this.movieRepository = movieRepository;
        this.scheduleRepository = scheduleRepository;
        this.statisticRepository = statisticRepository;
    }

    public synchronized List<Movie> showMovieList() {
        return showMovieList(0);
    }

    public synchronized List<Movie> showMovieList(Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        List<Schedule> scheduleList = scheduleRepository.findAll(pageable).getContent();

        List<Movie> movieList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            Movie movie = new Movie(schedule.getMovie());
            movie.setTime(schedule.getTime());
            movie.setPrice(schedule.getPrice());
            movie.setRemaining(schedule.getRemaining());
            movie.setVersion(schedule.getVersion());
            movie.setScheduleId(schedule.getId());
            movieList.add(movie);
        }

        return movieList;
    }

    public synchronized Movie getMovieByShowingId(int showingId) {
        return movieRepository.findById(showingId).orElse(null);
    }


    @Override
    @Transactional
    public synchronized void update(Observable observable, Object o) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Information information = (Information) o;
                reserve(information.getShowingId(), information.getCount(), information.getScheduleId());
            }
        };
        executorService.submit(runnable);
    }



    private synchronized void reserve(Integer showingId, int count, Integer scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);

        assert schedule != null;

        int remaining = schedule.getRemaining();
        if (remaining >= count) {
            remaining -= count;
        }
        schedule.setRemaining(remaining);

        scheduleRepository.save(schedule);



        Movie movie = movieRepository.findById(showingId).orElse(null);
        assert movie != null;
        Statistic statistic = movie.getStatistic();
        if (statistic == null) {
            statistic = new Statistic();
            statistic.setTicketSold(0).setSales(0).setName(movie.getName());
        }
        statistic.setTicketSold(statistic.getTicketsSold() + count);
        statistic.setSales(statistic.getSales() + count * schedule.getPrice());

        movie.setStatistic(statistic);
        statistic.setMovie(movie);

        movieRepository.save(movie);

    }



}
