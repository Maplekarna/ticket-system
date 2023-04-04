package com.bht.ticketsystem.entity.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @JsonProperty("order_id")
    private String orderId;


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
    @JoinColumn(name = "user_id")
    private User user;



    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

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



}
