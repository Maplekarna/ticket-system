package com.bht.ticketsystem.entity.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    @JsonProperty("order_id")
    private Integer orderId;


    @JsonProperty("movie_name")
    private String nameOfMovie;


    @JsonProperty("showing_id")
    private Integer showingId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("booking_time")
    private String bookingTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;


    @ManyToOne
    @JoinColumn(name = "schedule_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Schedule schedule;


    public Order setNameOfMovie(String nameOfMovie) {
        this.nameOfMovie = nameOfMovie;
        return this;
    }

    public Order setShowingId(int showingId) {
        this.showingId = showingId;
        return this;
    }

    public Order setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Order setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public Order setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
        return this;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }


    public Order setSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }


}
