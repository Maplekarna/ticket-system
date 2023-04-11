package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Exception.VersionCollisionException;
import com.bht.ticketsystem.Repository.OrderRepository;
import com.bht.ticketsystem.Repository.UserRepository;
import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Order;
import com.bht.ticketsystem.entity.db.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

@Service
public class OrderService extends Observable {
    private final MovieService movieService;
    private final UserRepository userRepository;



    @Autowired
    public OrderService(MovieService movieService, UserRepository userRepository) {
        this.movieService = movieService;
        this.userRepository = userRepository;

        addObserver(movieService);


    }

    public synchronized Set<Order> getOrderHistory(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return new HashSet<>();

        Set<Order> orders = user.getOrderSet();
        if (orders != null) {
            return orders;
        }


        return new HashSet<>();
    }


    @Transactional
    public synchronized void reserveSlots(String userId, Information information) throws VersionCollisionException {
        int showingId = information.getShowingId();
        int count = information.getCount();

        Movie movie = movieService.getMovieByShowingId(showingId);
        User user = userRepository.findById(userId).orElse(null);


        if (movie.getVersion().equals(information.getVersion())) {
            assert user != null;
            addOrderByUser(movie, user, count, getCurrentTime());
            setChanged();
            notifyObservers(information);
        } else {
            throw new VersionCollisionException();
        }


    }


    private static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dtf.format(currentTime);
    }



    @Transactional
    public synchronized void addOrderByUser(Movie movie, User user, int amount, String currentTime) {
        Order newOrder = new Order();

        newOrder.setNameOfMovie(movie.getName())
                .setShowingId(movie.getShowingId())
                .setNickname(user.getNickname())
                .setAmount(amount)
                .setBookingTime(currentTime)
                .setUser(user);

        user.getOrderSet().add(newOrder);
        userRepository.save(user);

    }



}
