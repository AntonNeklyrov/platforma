package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.Role;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.UserRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
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
    public long save(User user, byte[] salt) {
        var sql = """
                INSERT INTO users(first_name,last_name,card_number,email,password, role_id, salt)
                VALUES(:firstName,:lastName,:cardNumber,:email, :password, :roleId, :salt);
                """;
        var params = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("cardNumber", user.getCardNumber())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("roleId", 2)
                .addValue("salt", new String(salt, StandardCharsets.UTF_8));

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
                    card_number = :cardNumber,
                    email = :email,
                    password = :password,
                    role_id = :roleId
                WHERE id = :id;
                """;

        var params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("cardNumber", user.getCardNumber())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("roleId", user.getRole().getId());

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
                SELECT users.id as id,
                        users.first_name as first_name,
                        users.last_name as last_name,
                        users.card_number as card_number,
                        users.email as email,
                        users.password as password,
                        users.role_id as role_id,
                        dr.name as role_name
                FROM users
                        inner join dict_role dr on users.role_id = dr.id
                """;

        return jdbcTemplate.getJdbcOperations().query(sql, this::userMapper);
    }

    @Override
    public List<User> findBySubscriptionId(long subscriptionId) {
        var sql = """
                SELECT users.id as id,
                        users.first_name as first_name,
                        users.last_name as last_name,
                        users.card_number as card_number,
                        users.email as email,
                        users.password as password,
                        users.role_id as role_id,
                        users.salt as salt, 
                        dr.name as role_name
                FROM users
                    INNER JOIN subscription s on users.id = s.user_id
                    inner join dict_role dr on users.role_id = dr.id
                WHERE s.id = ?
                """;

        return jdbcTemplate.getJdbcOperations().query(sql, this::userMapper, subscriptionId);
    }

    @Override
    public Optional<User> findById(Long id) {
        var sql = """
                SELECT users.id as id,
                       users.first_name as first_name,
                       users.last_name as last_name,
                       users.card_number as card_number,
                       users.email as email,
                       users.password as password,
                       users.role_id as role_id,
                       users.salt as salt, 
                       dr.name as role_name
                 FROM users 
                 inner join dict_role dr on users.role_id = dr.id
                 WHERE users.id = ?;
                 """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::userMapper, id)
                .stream().findAny();
    }

    @Override
    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        var sql = """
                SELECT users.id as id,
                       users.first_name as first_name,
                       users.last_name as last_name,
                       users.card_number as card_number,
                       users.email as email,
                       users.password as password,
                       users.role_id as role_id,
                       users.salt as salt, 
                       dr.name as role_name
                 FROM users 
                 inner join dict_role dr on users.role_id = dr.id
                 WHERE email = ? AND password = ?;
                 """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::userMapper, email, password)
                .stream().findAny();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        var sql = """
                SELECT users.id as id,
                       users.first_name as first_name,
                       users.last_name as last_name,
                       users.card_number as card_number,
                       users.email as email,
                       users.password as password,
                       users.role_id as role_id,
                       users.salt as salt, 
                       dr.name as role_name
                 FROM users 
                  inner join dict_role dr on users.role_id = dr.id
                 WHERE email = ?;
                 """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::userMapper, email)
                .stream().findAny();
    }

    @Override
    public byte[] getSaltForUser(long id) {
        var sql = """
                select salt 
                from users
                where id = ?
                """;

        return jdbcTemplate.getJdbcTemplate().query(sql, (rs, rowNum) -> rs.getString("salt"), id).stream()
                .findAny()
                .get()
                .getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public List<Role> getUserRoles() {
        var sql = """
                select dict_role.id as role_id,
                       dict_role.name as role_name  
                from dict_role
                """;

        return jdbcTemplate.getJdbcTemplate().query(sql, this::roleMapper);
    }

    private User userMapper(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .cardNumber(rs.getString("card_number"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(Role.builder()
                        .id(rs.getLong("role_id"))
                        .name(rs.getString("role_name"))
                        .build())
                .salt(rs.getString("salt").getBytes())
                .build();
    }

    private Role roleMapper(ResultSet rs, int rowNum) throws SQLException {
        return Role.builder()
                .id(rs.getLong("role_id"))
                .name(rs.getString("role_name"))
                .build();
    }


}
