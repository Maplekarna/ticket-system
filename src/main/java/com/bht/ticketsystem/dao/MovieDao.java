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


    private Map<Integer, Movie> movieMap;

    @Autowired
    public MovieDao( ) {
        this.movieMap = new HashMap<>();
        createMoviesDBManually();
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
    public synchronized void update(Observable observable, Object o) {
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

    private void createMoviesDBManually() {

        Movie movie1 = new Movie();
        movie1.setShowingId(100).setName("Shushan Tale").setTime("2022-10-12 09:10:00").setRemaining(200).setPrice(150);

        Movie movie2 = new Movie();
        movie2.setShowingId(120).setName("Shushan Tale").setTime("2022-10-12 11:10:00").setRemaining(200).setPrice(150);

        Movie movie3 = new Movie();
        movie3.setShowingId(200).setName("Hero").setTime("2022-10-12 09:10:00").setRemaining(200).setPrice(120);

        Movie movie4 = new Movie();
        movie4.setShowingId(300).setName("Mechanic: Resurrection").setTime("2022-10-12 11:10:00").setRemaining(150).setPrice(300);

        movieMap.put(movie1.getShowingId(), movie1);
        movieMap.put(movie2.getShowingId(), movie2);
        movieMap.put(movie3.getShowingId(), movie3);
        movieMap.put(movie4.getShowingId(), movie4);

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
