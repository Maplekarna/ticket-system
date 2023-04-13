package com.bht.ticketsystem.entity.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.*;

import java.io.Serializable;

@Entity
@Table(name="movies")
public class Movie implements Serializable {

    @Id
    @JsonProperty("showing_id")
    @Column(name = "showing_id", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer showingId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("time")
    @Transient
    private String time;

    @JsonProperty("remaining")
    @Transient
    private Integer remaining;

    @JsonProperty("price")
    @Transient
    private Integer price;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Schedule> scheduleList;


    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnore
    private Statistic statistic;


    @Transient
    private Integer version;

    @Transient
    private Integer scheduleId;


    public Movie() {

    }

    public Movie(Movie movie) {
        this.showingId = movie.getShowingId();
        this.time = movie.getTime();
        this.name = movie.getName();
        this.remaining = movie.getRemaining();
        this.price = movie.getPrice();
        this.scheduleList = movie.getScheduleList();
        this.statistic = movie.getStatistic();
        this.version = movie.getVersion();
        this.scheduleId = movie.getScheduleId();

    }


    public Movie setShowingId(int showingId) {
        this.showingId = showingId;
        return this;
    }

    public Movie setName(String name) {
        this.name = name;
        return this;
    }

    public Movie setTime(String time) {
        this.time = time;
        return this;
    }

    public Movie setRemaining(int remaining) {
        this.remaining = remaining;
        return this;
    }

    public Movie setPrice(int price) {
        this.price = price;
        return this;
    }

    public Movie setStatistic(Statistic statistic) {
        this.statistic = statistic;
        return this;
    }

    public Movie setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Movie setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
        return this;
    }



    public int getShowingId() {
        return showingId;
    }

    public synchronized Integer getRemaining() {
        return remaining;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public Statistic getStatistic() {
        return statistic;
    }


    public Integer getVersion() {
        return version;
    }


    public Integer getScheduleId() {
        return scheduleId;
    }

}
