package com.neklyudov.platforma.service;


import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;

import java.util.List;

public interface LeagueService {

    Long addLeague(League league);

    List<League> getAllLeagues();

}
