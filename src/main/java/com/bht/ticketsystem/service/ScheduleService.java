package com.bht.ticketsystem.service;


import com.bht.ticketsystem.Repository.MovieRepository;
import com.bht.ticketsystem.Repository.ScheduleRepository;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, MovieRepository movieRepository) {
        this.scheduleRepository = scheduleRepository;
        this.movieRepository = movieRepository;
    }


    public void addSchedule(Integer showingId, String time, Integer remaining, Integer price) {
        Movie movie = movieRepository.findById(showingId).orElse(null);
        if (movie == null) return;

        List<Schedule> scheduleList = movie.getScheduleList();
        for (Schedule schedule : scheduleList) {
            if (schedule.getTime().equals(time)) return;
        }

        Schedule newSchedule = new Schedule();
        newSchedule.setTime(time).setRemaining(remaining).setPrice(price).setMovie(movie);

        movie.getScheduleList().add(newSchedule);

        scheduleRepository.save(newSchedule);

    }

}
