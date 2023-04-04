package com.bht.ticketsystem.entity.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="statistics")
public class Statistic implements Serializable {

    @Id
    private Integer showingId;
    private Integer ticketsSold;
    private Integer sales;


    public Statistic setShowingId(int showingId) {
        this.showingId = showingId;
        return this;
    }

    public Statistic setTicketSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
        return this;
    }

    public Statistic setSales(int sales) {
        this.sales = sales;
        return this;
    }


    public int getShowingId() {
        return showingId;
    }

    public synchronized int getTicketsSold() {
        return ticketsSold;
    }

    public int getSales() {
        return sales;
    }

    public synchronized int ticketsAdd(int x) {
        ticketsSold += x;
        return ticketsSold;
    }

    public synchronized int salesAdd(int x) {
        sales += x;
        return sales;
    }

}
