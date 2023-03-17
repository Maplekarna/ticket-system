package com.bht.ticketsystem.service;

import com.bht.ticketsystem.dao.MovieDao;
import com.bht.ticketsystem.entity.db.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieDao movieDao;

    @Autowired
    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;

    }

    public synchronized List<Movie> showMovieList() {
        return movieDao.getMovieList();
    }


}
