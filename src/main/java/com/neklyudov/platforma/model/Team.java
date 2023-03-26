package com.neklyudov.platforma.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Team {

    Long id;
    Long leagueId;
    String name;

    public Team(Long id, Long leagueId, String name) {
        this.id = id;
        this.leagueId = leagueId;
        this.name = name;
    }

    public Team() {
    }
}
