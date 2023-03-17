package com.bht.ticketsystem.service;

import com.bht.ticketsystem.dao.MovieDao;
import com.bht.ticketsystem.dao.OrderDao;
import com.bht.ticketsystem.dao.StatisticDao;
import com.bht.ticketsystem.dao.UserDao;
import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Movie;
import com.bht.ticketsystem.entity.db.Order;
import com.bht.ticketsystem.entity.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Observable;
import java.util.Set;

@Service
public class OrderService extends Observable {
    private final MovieDao movieDao;
    private final StatisticDao statisticDao;
    private final OrderDao orderDao;
    private final UserDao userDao;

    @Autowired
    public OrderService(MovieDao movieDao, StatisticDao statisticDao, OrderDao orderDao, UserDao userDao) {
        this.movieDao = movieDao;
        this.statisticDao = statisticDao;
        this.orderDao = orderDao;
        this.userDao = userDao;

        addObserver(movieDao);
        addObserver(statisticDao);


    }

    public synchronized Set<Order> getOrderHistory(String userId) {
        User user = userDao.getUserByUserId(userId);
        return orderDao.getOrderHistory(user);
    }


    /** Private Methods. */
    public synchronized void reserveSlots(int showingId, String userId, int count) {
        Movie movie = movieDao.getMovieByShowingId(showingId);
        User user = userDao.getUserByUserId(userId);

        //String orderId = orderDao.createOrder(movie.getName(), showingId, user.getNickname(), count, getCurrentTime(), user.getUserId());
        String orderId = orderDao.createOrder(movie, user, count, getCurrentTime());

        if (orderId != null) {
            Information information = new Information(showingId, count, movie.getPrice());
            setChanged();
            notifyObservers(information);

            System.out.println("Successfully. " + "\n" + "Your orderNumber is " + orderId);
        } else {
            System.out.println("Failed." + "\n" + "Cannot reserve tickets. ");
        }
    }


    private static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dtf.format(currentTime);
    }

}
