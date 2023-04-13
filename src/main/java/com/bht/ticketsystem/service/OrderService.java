package com.bht.ticketsystem.service;

import com.bht.ticketsystem.Exception.VersionCollisionException;
import com.bht.ticketsystem.Repository.OrderRepository;
import com.bht.ticketsystem.Repository.ScheduleRepository;
import com.bht.ticketsystem.Repository.UserRepository;
import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Order;
import com.bht.ticketsystem.entity.db.Schedule;
import com.bht.ticketsystem.entity.db.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderService extends Observable {
    private final MovieService movieService;
    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final ScheduleRepository scheduleRepository;



    @Autowired
    public OrderService(MovieService movieService, UserRepository userRepository, OrderRepository orderRepository, ScheduleRepository scheduleRepository) {
        this.movieService = movieService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.scheduleRepository = scheduleRepository;

        addObserver(movieService);


    }


    public synchronized List<Order> getOrderHistory(String userId) {
       return getOrderHistory(userId, 0);
    }

    public synchronized List<Order> getOrderHistory(String userId, Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        Slice<Order> orderSlice = orderRepository.findByUserId(userId, pageable);
        return orderSlice.getContent();

    }




    @Transactional
    public synchronized void reserveSlots(String userId, Information information) throws VersionCollisionException {
        int count = information.getCount();
        Integer scheduleId = information.getScheduleId();

        User user = userRepository.findById(userId).orElse(null);
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);

        assert schedule != null;
        if (schedule.getVersion().equals(information.getVersion())) {
            assert user != null;
            addOrderByUser(schedule, user, count, getCurrentTime());
            setChanged();
            notifyObservers(information);
        } else {
            throw new VersionCollisionException();
        }


    }

    @Transactional
    public synchronized void addOrderByUser(Schedule schedule, User user, int amount, String currentTime) {
        Order newOrder = new Order();

        Movie movie = schedule.getMovie();

        newOrder.setNameOfMovie(movie.getName())
                .setShowingId(movie.getShowingId())
                .setNickname(user.getNickname())
                .setAmount(amount)
                .setBookingTime(currentTime)
                .setUser(user);

        user.getOrderList().add(newOrder);
        userRepository.save(user);

    }


    private static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dtf.format(currentTime);
    }

}
