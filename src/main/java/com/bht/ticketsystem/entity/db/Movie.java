package com.bht.ticketsystem.entity.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="movies")
public class Movie implements Serializable {

    @Id
    private Integer showingId;

    private String name;
    private String time;
    private Integer remaining;
    private Integer price;

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

    public synchronized boolean changeRemaining(int x) {
        if (remaining >= x) {
            remaining -= x;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String str1 = "ShowingId: " + showingId + "\n";
        String str2 = "Name: " + name + "\n";
        String str3 = "Time: " + time + "\n";
        String str4 = "Remaining: " + remaining + "\n";
        String str5 = "Price: " + price + "\n";

        return str1 + str2 + str3 + str4 + str5;
    }

}
