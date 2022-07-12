package com.neklyudov.platforma.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Subscription {
    private Long id;
    private User user;
    private Double cost;
    private Integer period;
    private Date date;
    private League league;

    public Subscription(Long id, User user, Double cost, Integer period, Date date, League league) {
        this.id = id;
        this.user = user;
        this.cost = cost;
        this.period = period;
        this.date = date;
        this.league = league;
    }

    public Subscription() {

    }
}
