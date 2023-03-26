package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.Team;
import com.neklyudov.platforma.repository.TeamRepository;
import com.neklyudov.platforma.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public long addTeam(Team team) {
        return teamRepository.addTeam(team);
    }

    @Override
    public Team getTeamById(long id) {
        return teamRepository.getTeamById(id);
    }

    @Override
    public List<Team> getTeamsByLeagueId(long leagueId) {
        return teamRepository.getTeamsByLeagueId(leagueId);
    }
}
