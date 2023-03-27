package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.model.Role;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private List<Role> roleList;

    @Autowired
    public UserController(UserService userService, List<Role> roleList) {
        this.userService = userService;
    }

    @PostConstruct
    public void initialise() {
        roleList = userService.geUserRoles();
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

    @GetMapping("/update/{id}")
    public String showUpdateUserForm(Model model,
                                     HttpSession httpSession,
                                     @PathVariable Long id) {
        if ("false" == httpSession.getAttribute("isAdmin")) {
            model.addAttribute("forbiddenMessage", "У вас нет доступа к этому разделу");
            return "forbidden";
        }
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        model.addAttribute("updateUser", userService.getUserById(id));
        model.addAttribute("roles", roleList);
        return "adminTemplate/update-user-form";
    }

    @PutMapping("/{id}")
    public String updateUser(Model model,
                             HttpSession httpSession,
                             @ModelAttribute User user,
                             @PathVariable Long id) {
        user.setId(id);
        userService.updateUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "adminTemplate/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "adminTemplate/admin";
    }

    @GetMapping("/admin")
    public String showDashboard(Model model, HttpSession httpSession) {
        var users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "adminTemplate/admin";
    }

}
