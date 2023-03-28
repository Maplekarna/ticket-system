package com.bht.ticketsystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Information {

    @JsonProperty("showing_id")
    Integer showingId;

    @JsonProperty("count")
    Integer count;

    @JsonProperty("price")
    Integer price;




    public Integer getCount() {
        return count;
    }

    public Integer getShowingId() {
        return showingId;
    }

    public Integer getPrice() {
        return price;
    }
}
