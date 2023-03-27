package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.Role;
import com.neklyudov.platforma.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    long save(User user);
    void update(User user);
    void delete(long id);

    List<User> getAllUsers();
    List<User> findBySubscriptionId(long subscriptionId);
    Optional<User> findById(Long id);
    Optional<User> getUserByEmailAndPassword(String email, String password);
    Optional<User> findUserByEmail(String email);

    List<Role> getUserRoles();

}
