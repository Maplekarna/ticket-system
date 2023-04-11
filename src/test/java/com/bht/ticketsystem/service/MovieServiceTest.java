package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Repository.MovieRepository;
import com.bht.ticketsystem.Repository.StatisticRepository;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Schedule;

import com.bht.ticketsystem.entity.db.Statistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@SpringBootTest
class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    StatisticRepository statisticRepository;

    @Test
    void iniMoviesDBManually() {
        Movie movie1 = new Movie();
        movie1.setShowingId(100).setName("Shushan Tale").setTime("2022-10-12 09:10:00").setRemaining(200).setPrice(150);

        Statistic statistic1 = new Statistic();
        statistic1.setTicketSold(0).setSales(0);

        movie1.setStatistic(statistic1);
        statistic1.setMovie(movie1);

        movieRepository.save(movie1);
        //statisticRepository.save(statistic1);



//        Optional<Movie> movieOptional = movieRepository.findById(100);
//        if (movieOptional.isPresent()) {
//            Movie persistMovie = movieOptional.get();
//            statistic1.setMovie(persistMovie);
//            statisticRepository.save(statistic1);
//
//        }



        Movie movie2 = new Movie();
        movie2.setShowingId(120).setName("Shushan Tale").setTime("2022-10-12 11:10:00").setRemaining(200).setPrice(150);

        Movie movie3 = new Movie();
        movie3.setShowingId(200).setName("Hero").setTime("2022-10-12 09:10:00").setRemaining(200).setPrice(120);

        Movie movie4 = new Movie();
        movie4.setShowingId(300).setName("Mechanic: Resurrection").setTime("2022-10-12 11:10:00").setRemaining(150).setPrice(300);




        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
    }

    @Test
    void addSchedule() {
        Integer showingId = 100;
        String time = "2022-10-12 09:20:00";

        Movie movie = movieRepository.findById(showingId).orElse(null);
        if (movie == null) return;



        Set<Schedule> scheduleSet = movie.getScheduleSet();
        for (Schedule schedule : scheduleSet) {
            if (schedule.getTime().equals(time)) return;
        }

        Schedule newSchedule = new Schedule();
        newSchedule.setPrice(movie.getPrice()).setTime(time);

        newSchedule.setMovie(movie);

        movie.getScheduleSet().add(newSchedule);

        movieRepository.save(movie);
    }


}