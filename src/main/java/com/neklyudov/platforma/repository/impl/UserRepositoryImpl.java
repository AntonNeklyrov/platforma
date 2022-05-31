package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.UserRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.SQLException;

import java.sql.ResultSet;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(User user) {
        var sql = """
               INSERT INTO user(first_name,last_name,card_number,email,password)
               VALUES(:firstName,:lastName,:cardNumber,:email, :password);
               """;
        var params = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("cardNumber", user.getCardNumber())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public void update(User user) {
        var sql = """
                UPDATE user
                SET first_name =  :firstName,
                    last_name = :lastName,
                    card_number = :card_number,
                    email = :email,
                    password = :password
                WHERE id = :id;
                """;
        var params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("cardNumber", user.getCardNumber())
                .addValue("email" , user.getEmail())
                .addValue("password", user.getPassword());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(long id) {
        var sql = """
                DELETE
                FROM user
                WHERE id = ?;
                """;
        jdbcTemplate.getJdbcOperations().update(sql, id);
    }

    @Override
    public List<User> findBySubscriptionId(long subscriptionId) {
        var sql = """
                SELECT user.id,
                       user.first_name,
                       user.middle_name,
                       user.card_number,
                       user.email,
                       user.password
                FROM user
                    INNER JOIN subscription s on user.id = s.user_id
                WHERE s.user_id = ?
                """;

        return jdbcTemplate.getJdbcOperations().query(sql, this::userMapper, subscriptionId);
    }

    private User userMapper(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .cardNumber(rs.getString("card_number"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .build();
    }
}
