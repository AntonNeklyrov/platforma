package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.UserRepository;
import com.neklyudov.platforma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public List<User> getUsersBySubscriptionId(Long subscriptionId) {
        return userRepository.findBySubscriptionId(subscriptionId);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->new RuntimeException("Uer not found"));
    }

    @Override
    public Optional<Long> getUserByEmailAndPassword(User user) {
        Optional<User> optionalUser =  userRepository.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        User dbUser = optionalUser.get();
        return Optional.of(dbUser.getId());
    }

//    @Override
//    public Optional<Long> checkUserCanSignInAndGetIt(User user) {
//        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
//        if (optionalUser.isEmpty()) {
//            return Optional.empty();
//        }
//        User dbUser = optionalUser.get();
//        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
//            return Optional.of(dbUser.getId());
//        } else {
//            return Optional.empty();
//        }
//    }


}
