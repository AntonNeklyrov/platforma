package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.User;

import java.util.List;

public interface SubscriptionRepository {
    long save(Subscription subscription);
    void update(Subscription subscription);
    void delete(long id);

    List<Subscription> findAll();
    List<Subscription> findByLeagueId(long leagueId);
    List<Subscription> findByUserId(long userId);
}
