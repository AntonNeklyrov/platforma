package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.League;

import java.util.List;

public interface LeagueRepository {

    List<League> getAllLeagues();
    long addLeague(League league);
}
