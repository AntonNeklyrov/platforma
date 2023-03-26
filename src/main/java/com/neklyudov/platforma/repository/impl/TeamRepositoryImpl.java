package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.Team;
import com.neklyudov.platforma.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class TeamRepositoryImpl implements TeamRepository {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TeamRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long addTeam(Team team) {
        var sql = """
                insert into team (league_id, name)
                values (:leagueId, name)
                """;

        var param = new MapSqlParameterSource()
                .addValue("leagueId", team.getLeagueId())
                .addValue("name", team.getName());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, param, keyHolder);

        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public Team getTeamById(long id) {
        var sql = """
                select id, league_id, name
                from team
                where id = :id
                """;

        return jdbcTemplate.getJdbcTemplate().query(sql,
                        this::teamMapper,
                        id).stream()
                .findFirst()
                .orElse(new Team());
    }

    @Override
    public List<Team> getTeamsByLeagueId(long leagueId) {
        var sql = """
                select id, league_id, name
                from team
                where league_id = :id
                """;

        return jdbcTemplate.getJdbcTemplate().query(sql, this::teamMapper,leagueId);
    }


    private Team teamMapper(ResultSet rs, int num) throws SQLException {
        return Team.builder()
                .id(rs.getLong("id"))
                .leagueId(rs.getLong("league_id"))
                .name(rs.getString("name"))
                .build();
    }
}
