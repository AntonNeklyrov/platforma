package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {

    long addTeam(Team team);
    Team getTeamById(long id);
    List<Team> getTeamsByLeagueId(long leagueId);

}
