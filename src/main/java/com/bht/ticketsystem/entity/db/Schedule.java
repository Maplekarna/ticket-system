package com.bht.ticketsystem.entity.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "schedules")
public class Schedule implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(name = "time", nullable = false)
    private String time;


    @JsonProperty("remaining")
    @Column(name = "remaining", nullable = false)
    private Integer remaining;

    @JsonProperty("price")
    @Column(name = "price", nullable = false)
    private Integer price;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "showing_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Movie movie;


    @Version
    private Integer version;


    public String getTime() {
        return time;
    }

    public Movie getMovie() {
        return movie;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public Integer getPrice() {
        return price;
    }

    public Schedule setTime(String time) {
        this.time = time;
        return this;
    }
    public Schedule setRemaining(Integer remaining) {
        this.remaining = remaining;
        return this;
    }

    public Schedule setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Schedule setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public String toString() {
        String str1 = "id: " + id + "\n";
        String str2 = "time: " + time + "\n";
        String str3 = "showing_id: " + movie.getShowingId() + "\n";

        return str1 + str2 + str3;

    }
}
