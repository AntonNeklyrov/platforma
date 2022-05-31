package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    @Autowired
    public SubscriptionRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public long save(Subscription subscription) {
        return 0;
    }

    @Override
    public void update(Subscription subscription) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Subscription> findAll() {
        return null;
    }

    @Override
    public List<League> findByLeagueId(long leagueId) {
        return null;
    }

    @Override
    public List<User> findByUserId(long userId) {
        return null;
    }
}
