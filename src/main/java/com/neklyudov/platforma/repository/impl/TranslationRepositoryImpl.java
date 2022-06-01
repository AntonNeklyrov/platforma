package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.*;
import com.neklyudov.platforma.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TranslationRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Translation translation) {
        var sql = """
            INSERT INTO translation (user_id, league_id, commentator_id, guest_team,home_team,start_date,start_time)
            VALUES (:userId, :leagueId, :commentatorId, :guestTeam,:homeTeam,:startDate,:startTime);
            """;
        var params = new MapSqlParameterSource()
                .addValue("userId",translation.getUser().getId())
                .addValue("leagueId", translation.getLeague().getId())
                .addValue("commentatorId", translation.getCommentator().getId())
                .addValue("guestTeam", translation.getGuestTeam())
                .addValue("homeTeam",translation.getHomeTeam())
                .addValue("startDate", translation.getDate())
                .addValue("startTime", translation.getTime());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public void update(Translation translation) {
        var sql = """
                UPDATE translation
                SET user_id =  :userId,
                    league_id = :leagueId,  
                    commentator_id = :commentatorId,
                    guest_team = :guestTeam,
                    home_team = :homeTeam,
                    start_date = :startDate,
                    start_time = :startTime
                WHERE id = :id;
                """;

        var params = new MapSqlParameterSource()
                .addValue("userId",translation.getUser().getId())
                .addValue("leagueId", translation.getLeague().getId())
                .addValue("commentatorId", translation.getCommentator().getId())
                .addValue("guestTeam", translation.getGuestTeam())
                .addValue("homeTeam",translation.getHomeTeam())
                .addValue("startDate", translation.getDate())
                .addValue("startTime", translation.getTime());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(long id) {
        var sql = """
                DELETE
                FROM translation
                WHERE id = ?;
                """;
        jdbcTemplate.getJdbcOperations().update(sql, id);
    }

    @Override
    public List<Translation> findAll() {
        var sql = """
                SELECT translation.id,
                       translation.user_id,
                       translation.league_id,
                       translation.commentator_id,
                       translation.guest_team,
                       translation.home_team,
                       translation.start_date,
                       translation.start_time
                FROM translation;
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::translationMapper);
    }

    @Override
    public List<Translation> findByLeagueId(long leagueId) {
        var sql = """
                SELECT translation.id,
                       translation.user_id,
                       translation.league_id,
                       translation.commentator_id,
                       translation.guest_team,
                       translation.home_team,
                       translation.start_date,
                       translation.start_time
                FROM translation
                INNER JOIN league l on l.id = translation.league_id
                WHERE l.id = ?
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::translationMapper);
    }

    @Override
    public List<Translation> findByCommentatorId(long commentatorId) {
        var sql = """
                SELECT translation.id,
                       translation.user_id,
                       translation.league_id,
                       translation.commentator_id,
                       translation.guest_team,
                       translation.home_team,
                       translation.start_date,
                       translation.start_time
                FROM translation
                INNER JOIN commentator c on c.id = translation.commentator_id
                WHERE c.id = ?
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::translationMapper);
    }

    private Translation translationMapper(ResultSet rs, int rowNum) throws SQLException {
        return Translation.builder()
                .id(rs.getLong("id"))
                .user(User.builder()
                        .id(rs.getLong("user_id"))
                        .build())
                .league(League.builder()
                        .id(rs.getLong("league_id"))
                        .build())
                .commentator(Commentator.builder()
                        .id(rs.getLong("commentator_id"))
                        .build())
                .guestTeam(rs.getString("guest_team"))
                .homeTeam(rs.getString("home_team"))
                .date(rs.getDate("start_date"))
                .time(rs.getTime("start_time"))
                .build();
    }
}
