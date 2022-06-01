package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LeagueRepositoryImpl  implements LeagueRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LeagueRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<League> getAllLeagues() {
        var sql = """
                SELECT league.id,
                       league.name
                FROM league;
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::leagueMapper);
    }


    private League leagueMapper(ResultSet rs, int rowNum) throws SQLException {
        return League.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }
}