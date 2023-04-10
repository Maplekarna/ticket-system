package com.bht.ticketsystem.entity.db;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "schedules")
public class Schedule implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "time", nullable = false)
    private String time;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "showing_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Movie movie;


    public Schedule setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Schedule setTime(String time) {
        this.time = time;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
