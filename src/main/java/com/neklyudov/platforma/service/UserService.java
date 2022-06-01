package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    long addUser(User user);
    void updateUser(User user);
    void deleteUser(long id);

     List<User> getUsersBySubscriptionId(long subscriptionId);
     Optional<User> getUserById(long id);
     Optional<Long> getUserByEmail(User user);
}
