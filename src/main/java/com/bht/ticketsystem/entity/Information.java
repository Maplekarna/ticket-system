package com.bht.ticketsystem.entity;

public class Information {
    int showingId;
    int count;
    int price;
    public Information(int showingId, int count, int price) {
        this.showingId = showingId;
        this.count = count;
        this.price = price;
    }

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
