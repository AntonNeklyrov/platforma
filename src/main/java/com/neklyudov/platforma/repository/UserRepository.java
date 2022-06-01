package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    long save(User user);
    void update(User user);
    void delete(long id);

    List<User> findBySubscriptionId(long subscriptionId);
    Optional<User> getById(long id);
    Optional<User> getUserByEmail(String email);
}
