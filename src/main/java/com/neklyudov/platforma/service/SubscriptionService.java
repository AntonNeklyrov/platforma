package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.Translation;

import java.util.List;

public interface SubscriptionService {

    long addSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    void deleteSubscription(long id);

    Subscription getSubscriptionByUserId(long id);
    Subscription getSubscriptionByLeagueId(long id);

    List<Subscription> getAllSubscription();
}
