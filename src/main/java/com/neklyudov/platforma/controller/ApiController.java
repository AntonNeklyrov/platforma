package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.service.SubscriptionService;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = Logger.getGlobal();

    private final SubscriptionService subscriptionService;

    public ApiController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Timed(
            value = "qqq.request",
            histogram = true,
            percentiles = {0.95,0.99}
    )
    @RequestMapping(method = RequestMethod.GET,value = "/qqq", produces = {"application/json"})
    public List<Subscription> getAllSubscriptionsJSON(){
        return subscriptionService.getSubscriptionsByUserId(1L);
    }



}
