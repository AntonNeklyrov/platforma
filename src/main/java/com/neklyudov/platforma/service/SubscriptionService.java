package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.Translation;

import java.util.List;

public interface SubscriptionService {

    long addSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    void deleteSubscription(long id);

    List<Subscription> getSubscriptionsByUserId(long id);
    List<Subscription> getSubscriptionsByLeagueId(long id);

    List<Subscription> getAllSubscriptions();
}
