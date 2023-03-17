package com.bht.ticketsystem.dao;

import com.bht.ticketsystem.entity.Information;
import com.bht.ticketsystem.entity.db.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class MovieDao implements Observer {
    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();
    private final SessionFactory sessionFactory;

    private Map<Integer, Movie> movieMap;

    @Autowired
    public MovieDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.movieMap = new HashMap<>();
    }

    public synchronized List<Movie> getMovieList() {
        List<Movie> movieList = new ArrayList<>();

        for (Map.Entry<Integer, Movie> entry : movieMap.entrySet()) {
            movieList.add(entry.getValue());
        }

        return movieList;
    }

    public synchronized Movie getMovieByShowingId(int showingId) {
        return movieMap.get(showingId);
    }

    @Override
    public void update(Observable observable, Object o) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Information information = (Information) o;
                reserve(information.getShowingId(), information.getCount());
            }
        };
        executorService.submit(runnable);
    }

    private synchronized void reserve(int showingId, int count) {
            Movie movie = getMovieByShowingId(showingId);
            movie.changeRemaining(count);

    }

    /**


     private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();
     private final SessionFactory sessionFactory;

     @Autowired
     public MovieDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
     }


    public synchronized List<Movie> getMovieList() {
        List<Movie> movieList = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            movieList = session.createQuery("From Movie ", Movie.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return movieList;
    }

    public synchronized Movie getMovieByShowingId(int showingId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Movie.class, showingId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Observable observable, Object o) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Information information = (Information) o;
                reserve(information.getShowingId(), information.getCount());
            }
        };
        executorService.submit(runnable);
    }

    private synchronized void reserve(int showingId, int count) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Movie movie = session.get(Movie.class, showingId);
            movie.changeRemaining(count);

            session.beginTransaction();
            session.save(movie);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }

    }

    */

}
