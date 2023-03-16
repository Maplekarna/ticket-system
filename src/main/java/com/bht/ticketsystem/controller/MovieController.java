package com.bht.ticketsystem.controller;

import com.bht.ticketsystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public void printMovieListService() {
        movieService.printMovieList();
    }


}
