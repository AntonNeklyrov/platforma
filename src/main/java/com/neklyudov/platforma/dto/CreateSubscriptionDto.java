package com.neklyudov.platforma.dto;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.User;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;

import java.util.Date;

@Data
public class CreateSubscriptionDto {
    @NonNull
    public Double cost;
    @NonNull
    public Integer period;
    @NonNull
    public League league;
}
