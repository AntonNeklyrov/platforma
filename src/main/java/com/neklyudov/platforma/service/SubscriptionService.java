package com.neklyudov.platforma.service;

import com.neklyudov.platforma.dto.CreateSubscriptionDto;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.SubscriptionPage;
import com.neklyudov.platforma.model.Translation;

import java.util.Date;
import java.util.List;

public interface SubscriptionService {

    long addSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    void deleteSubscription(long id);

    List<Subscription> getSubscriptionsByUserId(Long id);
    List<Subscription> getSubscriptionsByLeagueId(Long id);
    void updateCostAndPeriodById(long id, Double cost, int period);
    List<Subscription> getAllSubscriptions();
    Long save(CreateSubscriptionDto subscriptionDto, Long subscriptionId);
    Subscription findById(Long id);

    SubscriptionPage findPage(int page);
}
