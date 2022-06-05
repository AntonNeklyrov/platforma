package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
 public class SubscriptionRepositoryImpl implements SubscriptionRepository {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SubscriptionRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public long save(Subscription subscription) {

        var sql = """
                INSERT INTO subscription (user_id,league_id,cost, period, date)
                VALUES (:userId, :leagueId, :cost, :period, :date);
                """;
        var params = new MapSqlParameterSource()
                .addValue("userId", subscription.getUser().getId())
                .addValue("leagueId", subscription.getLeague().getId())
                .addValue("cost", subscription.getCost())
                .addValue("period", subscription.getPeriod())
                .addValue("date", LocalDate.now());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public void update(Subscription subscription) {
        var sql = """
                UPDATE subscription
                SET user_id =  :userId,
                    league_id = :leagueId,  
                    cost = :cost,
                    period = :period,
                    date = :date
                WHERE id = :id;
                """;
        var params = new MapSqlParameterSource()
                .addValue("userId", subscription.getUser().getId())
                .addValue("leagueId", subscription.getLeague().getId())
                .addValue("cost", subscription.getCost())
                .addValue("period", subscription.getPeriod())
                .addValue("date", subscription.getDate());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(long id) {
        var sql = """
                DELETE
                FROM subscription
                WHERE id = ? AND subscription.user_id = 1;
                """;
        jdbcTemplate.getJdbcOperations().update(sql, id);
    }

    @Override
    public List<Subscription> findAll() {
        var sql = """
                SELECT subscription.id,
                       subscription.user_id,
                       subscription.league_id,
                       subscription.cost,
                       subscription.period,
                       subscription.date
                FROM subscription
                Where subscription.user_id = 1;
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::subscriptionMapper);
    }

    @Override
    public List<Subscription> findByLeagueId(Long leagueId) {
        var sql = """
                SELECT subscription.id,
                       subscription.user_id,
                       subscription.league_id,
                       subscription.cost,
                       subscription.period,
                       subscription.date
                FROM subscription
                    INNER JOIN league l on l.id = subscription.league_id
                WHERE l.id = ?
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::subscriptionMapper, leagueId);
    }

    @Override
    public List<Subscription> findAllByUserId(Long userId) {
        var sql = """
                SELECT subscription.id,
                       subscription.user_id,
                       subscription.league_id,
                       subscription.cost,
                       subscription.period,
                       subscription.date
                FROM subscription
                    INNER JOIN users u on u.id = subscription.user_id
                WHERE u.id = ?
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::subscriptionMapper,userId);
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        var sql  = """
                SELECT subscription.id,
                       subscription.user_id,
                       subscription.league_id,
                       subscription.cost,
                       subscription.period,
                       subscription.date
                FROM subscription
                Where id = ?;
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::subscriptionMapper, id).stream().findAny();
    }

    @Override
    public void updateCostAndDateById(Long id, Double cost, Date date) {
        var sql = """
                UPDATE subscription
                Set cost = :cost,
                    date = :date
                WHERE id = :id;
                """;
        var params = new MapSqlParameterSource()
                .addValue("cost", cost)
                .addValue("date", date);

        jdbcTemplate.update(sql, params);
    }

    private Subscription subscriptionMapper(ResultSet rs, int rowNum) throws SQLException {
        return Subscription.builder()
                .id(rs.getLong("id"))
                .user(User.builder()
                        .id(rs.getLong("user_id"))
                        .build())
                .league(League.builder()
                        .id(rs.getLong("league_id"))
                        .build())
                .cost(rs.getDouble("cost"))
                .period(rs.getInt("period"))
                .date(rs.getDate("date"))
                .build();
    }
}
