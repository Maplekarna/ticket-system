package com.bht.ticketsystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Information {

    @JsonProperty("showing_id")
    int showingId;

    @JsonProperty("count")
    int count;

    @JsonProperty("price")
    int price;




    public int getCount() {
        return count;
    }

    public int getShowingId() {
        return showingId;
    }

    public int getPrice() {
        return price;
    }
}
