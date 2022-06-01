package com.neklyudov.platforma.dto;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateSubscriptionDto {
    public Long id;
    public User user;
    public Double cost;
    public Integer period;
    public Date date;
    public League league;

    public static UpdateSubscriptionDto convert(Subscription subscription) {
        UpdateSubscriptionDto updateSubscriptionDto = new UpdateSubscriptionDto();
        updateSubscriptionDto.setId(subscription.getId());
        updateSubscriptionDto.setUser(subscription.getUser());
        updateSubscriptionDto.setCost(subscription.getCost());
        updateSubscriptionDto.setPeriod(subscription.getPeriod());
        updateSubscriptionDto.setDate(subscription.getDate());
        updateSubscriptionDto.setLeague(subscription.getLeague());
        return updateSubscriptionDto;
    }

}
