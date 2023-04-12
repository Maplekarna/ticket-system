package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Repository.MovieRepository;
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


import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MovieService implements Observer {

    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();


    private final MovieRepository movieRepository;


    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public synchronized List<Movie> showMovieList() {
        return showMovieList(0);
//        return movieRepository.findAll();
    }

    public synchronized List<Movie> showMovieList(Integer page) {
        Pageable pageable = PageRequest.of(page, 2);

        Slice<Movie> movieSlice = movieRepository.findAll(pageable);

        return movieSlice.getContent();
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
                reserve(information.getShowingId(), information.getCount());
            }
        };
        executorService.submit(runnable);
    }



    private synchronized void reserve(int showingId, int count) {
        Movie movie = getMovieByShowingId(showingId);

        int remaining = movie.getRemaining();
        if (remaining >= count) {
            remaining -= count;
        }
        movie.setRemaining(remaining);

        Statistic statistic = movie.getStatistic();
        if (statistic == null) {
            statistic = new Statistic();
            statistic.setTicketSold(0).setSales(0);
        }
        statistic.setTicketSold(statistic.getTicketsSold() + count);
        statistic.setSales(statistic.getSales() + count * movie.getPrice());

        movie.setStatistic(statistic);
        statistic.setMovie(movie);

        movieRepository.save(movie);

    }



    public void addScheduleByMovie() {
        Integer showingId = 100;
        String time = "2022-10-12 09:10:00";

        Movie movie = movieRepository.findById(showingId).orElse(null);
        if (movie == null) return;



        Set<Schedule> scheduleSet = movie.getScheduleSet();
        for (Schedule schedule : scheduleSet) {
            if (schedule.getTime().equals(time)) return;
        }

        Schedule newSchedule = new Schedule();
        newSchedule.setPrice(movie.getPrice()).setTime(time);

        //newSchedule.setMovie(movie);

        movie.getScheduleSet().add(newSchedule);

        movieRepository.save(movie);


    }


}
