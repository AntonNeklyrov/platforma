package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    long addUser(User user);
    void updateUser(User user);
    void deleteUser(long id);

     List<User> getUsersBySubscriptionId(Long subscriptionId);
     User getUserById(Long id);
     Optional<Long> getUserByEmailAndPassword(User user);

}
