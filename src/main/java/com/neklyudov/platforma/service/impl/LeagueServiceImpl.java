package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.repository.LeagueRepository;
import com.neklyudov.platforma.service.LeagueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository leagueRepository;
    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public Long addLeague(League league) {
        return leagueRepository.addLeague(league);
    }

    @Override
    public List<League> getAllLeagues() {
        return  leagueRepository.getAllLeagues();
    }
}
