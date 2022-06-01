package com.neklyudov.platforma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Subscription {
    private Long id;
    private User user;
    private Double cost;
    private Integer period;
    private Date date;
    private League league;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Subscription() {
    }

    public Subscription(League league) {
        this.league = league;
    }

    public static SubscriptionBuilder builder() {
        return new SubscriptionBuilder();
    }

    public static final class SubscriptionBuilder {
        private Long id;
        private User user;
        private Double cost;
        private Integer period;
        private Date date;
        private League league;

        private SubscriptionBuilder() {
        }

        public SubscriptionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SubscriptionBuilder user(User user) {
            this.user = user;
            return this;
        }

        public SubscriptionBuilder cost(Double cost) {
            this.cost = cost;
            return this;
        }

        public SubscriptionBuilder period(Integer period) {
            this.period = period;
            return this;
        }

        public SubscriptionBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public SubscriptionBuilder league(League league) {
            this.league = league;
            return this;
        }

        public Subscription build() {
            Subscription subscription = new Subscription();
            subscription.setId(id);
            subscription.setUser(user);
            subscription.setCost(cost);
            subscription.setPeriod(period);
            subscription.setDate(date);
            subscription.setLeague(league);
            return subscription;
        }
    }
}
