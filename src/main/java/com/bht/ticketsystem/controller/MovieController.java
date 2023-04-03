package com.bht.ticketsystem.controller;

import com.bht.ticketsystem.entity.ResultJSONObject;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ResultJSONObject showMovieListService(HttpServletRequest request, HttpServletResponse response)  {
        return ResultJSONObject.success(movieService.showMovieList());
    }


}
