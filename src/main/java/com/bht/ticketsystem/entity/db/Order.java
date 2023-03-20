package com.bht.ticketsystem.entity.db;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

//@Entity
//@Table(name = "orders")
public class Order implements Serializable {
//    @Id

    @JsonProperty("order_id")
    private String orderId;


    @JsonProperty("movie_name")
    private String nameOfMovie;


    @JsonProperty("showing_id")
    private Integer showingId;

//    @Column(name="nickname")
    @JsonProperty("nickname")
    private String nickname;

//    @Column(name="amount")
    @JsonProperty("count")
    private Integer count;

    @JsonProperty("booking_time")
    private String bookingTime;

    private String userId;


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

    public Order setCount(int count) {
        this.count = count;
        return this;
    }

    public Order setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
        return this;
    }

    public Order setUserId(String userId) {
        this.userId = userId;
        return this;
    }


    @Override
    public String toString() {
        String str1 = "Order number: " + orderId + "\n";
        String str2 = "Movie name: " + nameOfMovie + "\n";
        String str3 = "Showing id: " + showingId + "\n";
        String str4 = "Nickname: " + nickname + "\n";
        String str5 = "Count: " + count + "\n";
        String str6 = "Booking time: " + bookingTime + "\n";

        return str1 + str2 + str3 + str4 + str5 + str6;
    }

}
