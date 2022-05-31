package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.User;

import java.util.Optional;

public interface UserRepository {
    long save(User user);
    void update(User user);
    void delete(long id);

    Optional<User> findBySubscriptionId(long subscriptionId);
}
