package com.bht.ticketsystem.controller;

import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> showMovieListService(HttpServletRequest request, HttpServletResponse response)  {
//        HttpSession session = request.getSession(false);
//
//        if (session == null) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            return new ArrayList<>();
//        }

        return movieService.showMovieList();


    }


}
