package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.repository.CommentatorRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentatorRepositoryImpl implements CommentatorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    CommentatorRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Commentator commentator) {
        var sql = """
            INSERT INTO commentator (first_name, last_name, email, password)
            VALUES (:first_name, :last_name, :email, :password);
            """;
        var params = new MapSqlParameterSource()
                .addValue("firstName", commentator.getFirstName())
                .addValue("lastName", commentator.getLastName())
                .addValue("email", commentator.getEmail())
                .addValue("password", commentator.getPassword());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public void update(Commentator commentator) {
        var sql = """
                UPDATE commentator
                SET first_name =  :firstName,
                    last_name = :lastName,  
                    email = :email,
                    password = :password
                WHERE id = :id;
                """;
        var params = new MapSqlParameterSource()
                .addValue("id", commentator.getId())
                .addValue("firstName", commentator.getFirstName())
                .addValue("lastName", commentator.getLastName())
                .addValue("email" , commentator.getEmail())
                .addValue("password", commentator.getPassword());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(long id) {
        var sql = """
                DELETE
                FROM commentator
                WHERE id = ?;
                """;
        jdbcTemplate.getJdbcOperations().update(sql, id);
    }

    @Override
    public List<Commentator> findAll() {
        var sql = """
                SELECT commentator.id,
                       commentator.first_name,
                       commentator.last_name,
                       commentator.email,
                       commentator.password
                FROM commentator;
                """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::commentatorMapper);
    }

    @Override
    public Optional<Commentator> findById(long id) {
        var sql = """
                   SELECT commentator.id,
                       commentator.first_name,
                       commentator.last_name,
                       commentator.email,
                       commentator.password
                    FROM commentator;
                    WHERE id = ?;
                    """;
        return jdbcTemplate.getJdbcTemplate().query(sql, this::commentatorMapper, id)
                .stream().findAny();
    }

    private Commentator commentatorMapper(ResultSet rs, int rowNum) throws SQLException {
        return Commentator.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .build();
    }
}
