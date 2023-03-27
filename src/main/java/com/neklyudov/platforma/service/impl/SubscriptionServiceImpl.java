package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.dto.CreateSubscriptionDto;
import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.SubscriptionPage;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.SubscriptionRepository;
import com.neklyudov.platforma.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Subscription> getSubscriptionsByUserId(Long id) {
        return subscriptionRepository.findAllByUserId(id);
    }

    @Override
    public List<Subscription> getSubscriptionsByLeagueId(Long id)
    {
        return subscriptionRepository.findByLeagueId(id);
    }

    @Override
    public void updateCostAndPeriodById(long id, Double cost, int period) {
        subscriptionRepository.updateCostAndPeriodById(id,cost,period);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {

        return null;
    }

    @Override
    public Long save(CreateSubscriptionDto subscriptionDto, Long userId) {
        Subscription subscription = Subscription.builder()
                .cost(subscriptionDto.getCost())
                .period(subscriptionDto.getPeriod())
                .league(League.builder().id(subscriptionDto.getLeagueId()).name(subscriptionDto.getLeagueName()).build())
                .user(User.builder().id(userId).build())
                .build();

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription findById(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(()->new RuntimeException("subscription not found"));
    }

    @Override
    public SubscriptionPage findPage(int page) {
        return subscriptionRepository.findPage(page);
    }


}
