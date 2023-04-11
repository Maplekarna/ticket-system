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
    private Integer showingId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("time")
    private String time;

    @JsonProperty("remaining")
    private Integer remaining;

    @JsonProperty("price")
    private Integer price;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Schedule> scheduleSet;


    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnore
    private Statistic statistic;


    @Version
    private Integer version;


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

    public void setVersion(Integer version) {
        this.version = version;
    }



    public int getShowingId() {
        return showingId;
    }

    public synchronized int getRemaining() {
        return remaining;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public Set<Schedule> getScheduleSet() {
        return scheduleSet;
    }

    public Statistic getStatistic() {
        return statistic;
    }



    public Integer getVersion() {
        return version;
    }


    @Override
    public String toString() {
        return "Movie name:" + getName();
    }

}
