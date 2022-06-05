package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Slf4j
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectFromRoot() {
        return "redirect:/main";
    }

    @GetMapping("/sign-in")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user, HttpSession httpSession) {
        long userId = userService.addUser(user);
        httpSession.setAttribute("userId", userId);
        return "redirect:/main";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute User user, HttpSession httpSession) {
        Optional<Long> optionalUserId = userService.getUserByEmailAndPassword(user);
        httpSession.setAttribute("userId", optionalUserId.get());
        return "redirect:/main";
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("userId");
        httpSession.removeAttribute("isAdmin");
        return "redirect:/main";
    }

}
