package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LeagueRepositoryImpl  implements LeagueRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LeagueRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<League> getAllLeagues() {
        var sql = """
                SELECT league.id,
                       league.name,
                       league.country
                FROM league;
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::leagueMapper);
    }

    @Override
    public long addLeague(League league) {
        var sql = """
                insert into league (name, country)
                values (:name, :country)
                """;

        var param = new MapSqlParameterSource()
                .addValue("name",league.getName())
                .addValue("country", league.getCountry());

        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return (long) keyHolder.getKeys().get("id");
    }


    private League leagueMapper(ResultSet rs, int rowNum) throws SQLException {
        return League.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .country(rs.getString("country"))
                .build();
    }
}
