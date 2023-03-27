package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.SubscriptionPage;
import com.neklyudov.platforma.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {
    long save(Subscription subscription);
    void update(Subscription subscription);
    void delete(long id);

    List<Subscription> findAll();
    List<Subscription> findByLeagueId(Long leagueId);
    void updateCostAndPeriodById(Long id, Double cost, int period);
    List<Subscription> findAllByUserId(Long userId);
    Optional<Subscription> findById(Long id);

    Integer getCount();

    SubscriptionPage findPage(int page);


}
