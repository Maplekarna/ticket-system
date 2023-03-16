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
        return orderDao.getOrderHistory(userId);
    }


    public synchronized void reserveSlots() {
        System.out.println("\nPlease enter information(type '0' to return):");

        int sessionId = produceSessionId();
        if (sessionId == 0 || sessionId == -1) return;

        int userId = produceUserId();
        if (userId == 0 || userId == -1) return;

        int count = produceCount(sessionId);
        if (count == 0 || count == -1) return;

        reserveSlotsByInformation(sessionId, userId, count);
    }



    /** Private Methods. */
    private void reserveSlotsByInformation(int sessionId, int userId, int count) {
        Movie movie = movieDao.getMovieByShowingId(sessionId);
        User user = userDao.getUserByUserId(userId);

        String orderId = orderDao.createOrder(movie.getName(), sessionId, user.getUserId(), count, getCurrentTime());

        if (orderId != null) {
            Information information = new Information(sessionId, count, movie.getPrice());
            setChanged();
            notifyObservers(information);

            System.out.println("Successfully. " + "\n" + "Your orderNumber is " + orderId);
        } else {
            System.out.println("Failed." + "\n" + "Cannot reserve tickets. ");
        }
    }




    private boolean checkSessionValid(int sessionId) {
        Movie movie = movieDao.getMovieByShowingId(sessionId);
        if (movie == null) {
            System.out.println("Failed." + "\n" + "Input session is invalid. \n");
            return false;
        }
        return true;
    }

    private boolean checkUserIdValid(int userId) {
        User user = userDao.getUserByUserId(userId);
        if (user == null) {
            System.out.println("Failed." + "\n" + "User doesn't exist. \n");
            return false;
        }
        return true;
    }

    private boolean checkCountValid(int sessionId, int count) {
        if (count <= 0) {
            System.out.println("Failed." + "\n" + "Input count is invalid. \n");
            return false;
        }

        Movie movie = movieDao.getMovieByShowingId(sessionId);
        if (movie.getRemaining() < count) {
            System.out.println("Failed." + "\n" + "There are not enough tickets left.\n");
            return false;
        }
        return true;
    }

    private static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dtf.format(currentTime);
    }

}
