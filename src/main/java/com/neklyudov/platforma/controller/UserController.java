package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.dto.UpdateSubscriptionDto;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/update")
    public String showUpdateUserForm(Model model, @PathVariable long id) {
        model.addAttribute("updateUser", userService.getUserById(id));
        return "admin/update-user-form";
    }

    @PostMapping("/update")
    public String updateUser(Model model, HttpSession httpSession, @RequestBody User user) {
        userService.updateUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String showDashboard(Model model, HttpSession httpSession) {
        var users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/admin";
    }

}
