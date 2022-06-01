package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.UserRepository;
import com.neklyudov.platforma.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserRepository userRepository1) {
        this.userRepository = userRepository1;
    }

    @Override
    public long addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> getUsersBySubscriptionId(long subscriptionId) {

        return userRepository.findBySubscriptionId(subscriptionId);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public Optional<Long> getUserByEmail(User user) {
        Optional<User> optionalUser =  userRepository.getUserByEmail(user.getEmail());
        User dbUser = optionalUser.get();
        return Optional.of(dbUser.getId());
    }


}
