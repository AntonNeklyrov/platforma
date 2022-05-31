package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.User;

public interface UserService {

    long addUser(User author);
    void updateUser(User user);
    void deleteUser(long id);

    User getUserById(long id);


}
