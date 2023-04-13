package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Repository.MovieRepository;
import com.bht.ticketsystem.Repository.ScheduleRepository;
import com.bht.ticketsystem.Repository.StatisticRepository;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
        movie1.setName("Shushan Tale").setRemaining(200).setPrice(150);

        Movie movie2 = new Movie();
        movie2.setName("Hero").setRemaining(200).setPrice(120);

        Movie movie3 = new Movie();
        movie3.setName("Mechanic: Resurrection").setRemaining(150).setPrice(300);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
    }




}