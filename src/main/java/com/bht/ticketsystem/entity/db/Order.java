package com.bht.ticketsystem.entity.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    private String orderId;
    private String nameOfMovie;
    private Integer showingId;
    @Column(name="nickname")
    private String receiver;
    @Column(name="amount")
    private Integer count;
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

    public Order setReceiver(String receiver) {
        this.receiver = receiver;
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

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }


    @Override
    public String toString() {
        String str1 = "OrderNumber: " + orderId + "\n";
        String str2 = "NameOfMovie: " + nameOfMovie + "\n";
        String str3 = "ShowingId: " + showingId + "\n";
        String str4 = "Receiver: " + receiver + "\n";
        String str5 = "Count: " + count + "\n";
        String str6 = "BookingTime: " + bookingTime + "\n";

        return str1 + str2 + str3 + str4 + str5 + str6;
    }

}
