package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public long createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/search")
    public List<User> getUserBySubscriptionId(@RequestParam Optional<Long> subscriptionId) {
        if(subscriptionId.isEmpty()){
            throw  new RuntimeException();
        }
        return userService.getUsersBySubscriptionId(subscriptionId.get());
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
