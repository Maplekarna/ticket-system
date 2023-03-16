package com.bht.ticketsystem.dao;

import com.bht.ticketsystem.entity.db.Order;
import com.bht.ticketsystem.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class OrderDao {
    private String latestOrderId;
    private final SessionFactory sessionFactory;
    @Autowired
    public OrderDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        latestOrderId = "100000";
    }

    public Set<Order> getOrderHistory(String userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userId).getOrderSet();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return new HashSet<>();
    }

    public synchronized String createOrder(String nameOfMovie, int showingId, String username, int count, String currentTime) {
        String orderId = createOrderId();

        Order newOrder = new Order();
        newOrder.setOrderId(orderId)
                .setNameOfMovie(nameOfMovie)
                .setShowingId(showingId)
                .setReceiver(username)
                .setCount(count)
                .setBookingTime(currentTime);

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(newOrder);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        latestOrderId = orderId;

        return orderId;

    }

    private String createOrderId() {
        char[] array = latestOrderId.toCharArray();
        StringBuilder sb = new StringBuilder();
        int z = 1;
        for (int i = array.length - 1; i >= 0; i--) {
            int sum = (array[i] - '0') + z;
            int a = sum % 10;
            z = sum / 10;
            sb.append(a);
        }
        if (z != 0) sb.append(z);
        return sb.reverse().toString();
    }


}
