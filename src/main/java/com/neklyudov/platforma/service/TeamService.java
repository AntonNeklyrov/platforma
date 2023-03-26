package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.Team;

import java.util.List;

public interface TeamService {

    long addTeam(Team team);
    Team getTeamById(long id);
    List<Team> getTeamsByLeagueId(long leagueId);

}
