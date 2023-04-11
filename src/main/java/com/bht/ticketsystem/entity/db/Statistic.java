package com.bht.ticketsystem.entity.db;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="statistics")
public class Statistic implements Serializable {

    @Id
    //@Column(name = "id", nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showing_id", nullable = false)
    private Integer showingId;


    @Column(name = "tickets_sold", nullable = false)
    private Integer ticketsSold;


    @Column(name = "sales", nullable = false)
    private Integer sales;


    @OneToOne
    @MapsId
    @JoinColumn(name = "showing_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Movie movie;



    public Statistic setShowingId(int showingId) {
        this.showingId = showingId;
        return this;
    }

    public Statistic setTicketSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
        return this;
    }

    public Statistic setSales(int sales) {
        this.sales = sales;
        return this;
    }

    public Statistic setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }



    public int getShowingId() {
        return showingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public synchronized int getTicketsSold() {
        return ticketsSold;
    }

    public int getSales() {
        return sales;
    }


}
