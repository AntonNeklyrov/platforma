package com.neklyudov.platforma.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subscription {
    private Long id;
    private User user;
    private Double cost;
    private Integer period;
    private Integer date;
    private League league;
}
