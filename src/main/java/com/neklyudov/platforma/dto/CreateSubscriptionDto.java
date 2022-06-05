package com.neklyudov.platforma.dto;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubscriptionDto {
    @NonNull
    public Double cost;
    @NonNull
    public Integer period;
    @NonNull
    public Long leagueId;

}
