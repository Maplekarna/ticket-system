package com.bht.ticketsystem.entity.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Null;

import java.io.Serializable;

//@Entity
//@Table(name="movies")
public class Movie implements Serializable {

    //@Id

    @JsonProperty("showing_id")
    private Integer showingId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("time")
    private String time;

    @JsonProperty("remaining")
    private Integer remaining;

    @JsonProperty("price")
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
