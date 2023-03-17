package com.bht.ticketsystem.dao;

import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Statistic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Repository
public class StatisticDao implements Observer {
    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();

    private final SessionFactory sessionFactory;

    private Map<Integer, Statistic> statisticMap;

    @Autowired
    public StatisticDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.statisticMap = new HashMap<>();
    }


    public synchronized List<Statistic> getStatistics() {
        List<Statistic> statisticList = new ArrayList<>();

       for (Map.Entry<Integer, Statistic> entry : statisticMap.entrySet()) {
           statisticList.add(entry.getValue());
       }

        return statisticList;
    }

    @Override
    public void update(Observable observable, Object o) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Information information = (Information) o;
                updateStatistic(information.getShowingId(), information.getCount(), information.getPrice());
            }
        };
        executorService.submit(runnable);
    }

    private void updateStatistic(int showingId, int count, int price) {
        if (!statisticMap.containsKey(showingId)) {
            Statistic statistic = new Statistic();
            statistic.setShowingId(showingId).setTicketSold(0).setSales(0);
            statisticMap.put(showingId, statistic);
        }

        Statistic statistic = statisticMap.get(showingId);

        statistic.ticketsAdd(count);
        statistic.salesAdd(count * price);


    }

//    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public StatisticDao(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//
//    public synchronized List<Statistic> getStatistics() {
//        List<Statistic> statisticList = new ArrayList<>();
//
//        try (Session session = sessionFactory.openSession()) {
//            statisticList = session.createQuery("From Statistic", Statistic.class).getResultList();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return statisticList;
//    }
//
//    @Override
//    public void update(Observable observable, Object o) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Information information = (Information) o;
//                updateStatistic(information.getShowingId(), information.getCount(), information.getPrice());
//            }
//        };
//        executorService.submit(runnable);
//    }
//
//    private void updateStatistic(int showingId, int count, int price) {
//        Session session = null;
//
//        try {
//            session =sessionFactory.openSession();
//
//            Statistic statistic =session.get(Statistic.class, showingId);
//            if (statistic == null) {
//                statistic = new Statistic();
//                statistic.setShowingId(showingId).setTicketSold(0).setSales(0);
//
//            }
//            statistic.ticketsAdd(count);
//            statistic.salesAdd(count * price);
//
//            session.beginTransaction();
//            session.save(statistic);
//            session.getTransaction().commit();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            session.getTransaction().rollback();
//        } finally {
//            if (session != null) session.close();
//        }
//
//
//    }

}
