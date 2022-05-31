package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.UserRepository;
import com.neklyudov.platforma.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long addUser(User author) {
        return 0;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public User getUserById(long id) {
        return null;
    }


}
