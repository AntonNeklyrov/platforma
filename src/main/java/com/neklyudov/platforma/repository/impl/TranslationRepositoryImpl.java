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
import java.sql.Time;
import java.util.List;
import java.util.Optional;

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
    public void delete(Long id) {
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
                       translation.start_time,
                       l.id AS league_id,
                       l.name AS name,
                       c.id AS commentator_id,
                       c.first_name,
                       c.last_name
                FROM translation
                Inner JOIN users u on u.id = translation.user_id
                inner join league l on l.id = translation.league_id
                INNER JOIN commentator c on c.id = translation.commentator_id
                WHERE u.id = 1;
                """;

        return jdbcTemplate.getJdbcTemplate().query(sql, this::translationMapper);
    }

    @Override
    public List<Translation> findByLeagueId(Long leagueId) {
        var sql = """
                SELECT translation.id,
                       translation.user_id,
                       translation.league_id,
                       translation.commentator_id,
                       translation.guest_team,
                       translation.home_team,
                       translation.start_date,
                       translation.start_time,
                       l.id AS league_id,
                       l.name AS name,
                       c.id AS commentator_id,
                       c.first_name,
                       c.last_name
                FROM translation
                INNER JOIN league l on l.id = translation.league_id
                INNER JOIN commentator c on c.id = translation.commentator_id
                WHERE l.id = ?
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::translationMapper);
    }

    @Override
    public List<Translation> findByCommentatorId(Long commentatorId) {
        var sql = """
                SELECT translation.id,
                       translation.user_id,
                       translation.league_id,
                       translation.commentator_id,
                       translation.guest_team,
                       translation.home_team,
                       translation.start_date,
                       translation.start_time,
                       l.id AS league_id,
                       l.name AS name,
                       c.id AS commentator_id,
                       c.first_name,
                       c.last_name
                FROM translation
                INNER JOIN commentator c on c.id = translation.commentator_id
                inner join league l on l.id = translation.league_id
                WHERE c.id = ?
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::translationMapper);
    }

    @Override
    public Optional<Translation> findById(Long Id) {
        var sql = """
                SELECT translation.id,
                       translation.user_id,
                       translation.league_id,
                       translation.commentator_id,
                       translation.guest_team,
                       translation.home_team,
                       translation.start_date,
                       translation.start_time,
                      l.id AS league_id,
                       l.name AS name,
                       c.id AS commentator_id,
                       c.first_name,
                       c.last_name
                FROM translation
                inner join league l on l.id = translation.league_id
                inner join commentator c on c.id = translation.commentator_id
                WHERE translation.id = ?
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::translationMapper, Id)
                .stream().findAny();
    }

    @Override
    public List<Translation> findAllByUserId(Long userId) {
        var sql = """
                SELECT distinct (translation.id),
                       translation.user_id,
                       translation.league_id,
                       translation.commentator_id,
                       translation.guest_team,
                       translation.home_team,
                       translation.start_date,
                       translation.start_time,
                       l.id AS league_id,
                       l.name AS name,
                       c.id AS commentator_id,
                       c.first_name,
                       c.last_name
                from subscription
                inner join league l on l.id = subscription.league_id
                inner join translation  on l.id = translation.league_id
                inner join commentator c on translation.commentator_id = c.id
                where subscription.user_id = ?
              
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::translationMapper, userId);

    }

    @Override
    public void updateTime(Long id, Time time) {
        var sql = """
                UPDATE translation
                SET start_time = :startTime
                WHERE id = :id;
                """;

        var params = new MapSqlParameterSource()
                .addValue("startTime", time);
        jdbcTemplate.update(sql, params);
    }

    private Translation translationMapper(ResultSet rs, int rowNum) throws SQLException {
        return Translation.builder()
                .id(rs.getLong("id"))
                .user(User.builder()
                        .id(rs.getLong("user_id"))
                        .build())
                .league(League.builder()
                        .id(rs.getLong("league_id"))
                        .name(rs.getString("name"))
                        .build())
                .commentator(Commentator.builder()
                        .id(rs.getLong("commentator_id"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .build())
                .guestTeam(rs.getString("guest_team"))
                .homeTeam(rs.getString("home_team"))
                .date(rs.getDate("start_date").toLocalDate())
                .time(rs.getTime("start_time").toLocalTime())
                .build();
    }
}
