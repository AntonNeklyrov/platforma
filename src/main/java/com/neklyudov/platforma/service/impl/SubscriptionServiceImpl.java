package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.repository.SubscriptionRepository;
import com.neklyudov.platforma.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public long addSubscription(Subscription subscription) {

        return subscriptionRepository.save(subscription);
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        subscriptionRepository.update(subscription);
    }

    @Override
    public void deleteSubscription(long id) {
        subscriptionRepository.delete(id);
    }

    @Override
    public List<Subscription> getSubscriptionsByUserId(long id) {
        return subscriptionRepository.findByUserId(id);
    }

    @Override
    public List<Subscription> getSubscriptionsByLeagueId(long id)
    {
        return subscriptionRepository.findByLeagueId(id);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {

        return null;
    }
}
