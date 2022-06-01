package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.service.SubscriptionService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public long createSubscription(Model model,
                                   @RequestBody Subscription subscription) {
        return subscriptionService.addSubscription(subscription);
    }

    @PutMapping
    public void updateSubscription(@RequestBody Subscription subscription){
        subscriptionService.updateSubscription(subscription);
    }

    @DeleteMapping
    public void deleteSubscription(long id){
        subscriptionService.deleteSubscription(id);
    }

    @GetMapping
    public List<Subscription> getBooks() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/search")
    public List<Subscription> getSu(@RequestParam Optional<Long> userId,
                                 @RequestParam Optional<Long>  leagueId) {
        if (userId.isEmpty() && leagueId.isEmpty() || userId.isPresent() && leagueId.isPresent()) {
            throw new RuntimeException();
        }

        if (userId.isPresent()) {
            return subscriptionService.getSubscriptionsByUserId(userId.get());
        }

        return subscriptionService.getSubscriptionsByLeagueId(leagueId.get());
    }
}
