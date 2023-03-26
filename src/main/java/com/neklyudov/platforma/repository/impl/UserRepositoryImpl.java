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
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(User user) {
        var sql = """
               INSERT INTO users(first_name,last_name,card_number,email,password, role)
               VALUES(:firstName,:lastName,:cardNumber,:email, :password, :role);
               """;
        var params = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("cardNumber", user.getCardNumber())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("role", "Пользователь");

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public void update(User user) {
        var sql = """
                UPDATE users
                SET first_name =  :firstName,
                    last_name = :lastName,
                    card_number = :card_number,
                    email = :email,
                    password = :password,
                    role = :role
                WHERE id = :id;
                """;

        var params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("cardNumber", user.getCardNumber())
                .addValue("email" , user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("role", user.getRole());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(long id) {
        var sql = """
                DELETE
                FROM users
                WHERE id = ?;
                """;
        jdbcTemplate.getJdbcOperations().update(sql, id);
    }

    @Override
    public List<User> getAllUsers() {
        var sql = """
                SELECT users.id,
                       users.first_name,
                       users.last_name,
                       users.card_number,
                       users.email,
                       users.password,
                       users.role
                FROM users
                """;

        return jdbcTemplate.getJdbcOperations().query(sql, this::userMapper);
    }

    @Override
    public List<User> findBySubscriptionId(long subscriptionId) {
        var sql = """
                SELECT users.id,
                       users.first_name,
                       users.last_name,
                       users.card_number,
                       users.email,
                       users.password,
                       users.role
                FROM users
                    INNER JOIN subscription s on users.id = s.user_id
                WHERE s.id = ?
                """;

        return jdbcTemplate.getJdbcOperations().query(sql, this::userMapper, subscriptionId);
    }

    @Override
    public Optional<User>  findById(Long id) {
        var sql = """
                   SELECT users.id,
                          users.first_name,
                          users.last_name,
                          users.card_number,
                          users.email,
                          users.password,
                          users.role
                    FROM users 
                    WHERE id = ?;
                    """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::userMapper, id)
                .stream().findAny();
    }

    @Override
    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        var sql = """
                   SELECT users.id,
                          users.first_name,
                          users.last_name,
                          users.card_number,
                          users.email,
                          users.password
                    FROM users 
                    WHERE email = ? AND password = ? 
                           ;
                    """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::userMapper, email, password)
                .stream().findAny();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        var sql = """
                   SELECT users.id,
                          users.first_name,
                          users.last_name,
                          users.card_number,
                          users.email,
                          users.password
                    FROM users 
                    WHERE email = ?;
                    """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::userMapper, email)
                .stream().findAny();
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
