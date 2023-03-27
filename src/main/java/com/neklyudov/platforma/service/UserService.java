package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.Role;
import com.neklyudov.platforma.model.User;

import java.util.List;

public interface UserService {

    long addUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    List<User> getUsersBySubscriptionId(Long subscriptionId);

    User getUserById(Long id);

    User getUserByEmailAndPassword(User user);

    List<Role> geUserRoles();

}
