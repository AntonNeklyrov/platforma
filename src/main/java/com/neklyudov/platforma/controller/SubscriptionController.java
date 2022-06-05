package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.dto.CreateSubscriptionDto;
import com.neklyudov.platforma.dto.UpdateSubscriptionDto;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.service.LeagueService;
import com.neklyudov.platforma.service.SubscriptionService;
import com.neklyudov.platforma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final LeagueService leagueService;
    private final UserService userService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, LeagueService leagueService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.leagueService = leagueService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String showSubscriptionForm(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        model.addAttribute("subscription", new CreateSubscriptionDto());
        model.addAttribute("leagues", leagueService.getAllLeagues());
        return "subscription/subscription-form";
    }

    @PostMapping
    public String createSubscription(@ModelAttribute CreateSubscriptionDto subscriptionDto,
                                     Model model,
                                     HttpSession httpSession
                                    ) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        subscriptionService.save(subscriptionDto, 1L);
        return "redirect:/subscriptions";
    }

    @GetMapping("/update")
    public String showSubscriptionUpdateForm(Model model, @RequestParam long id, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        model.addAttribute("updateSubscription", UpdateSubscriptionDto.convert(subscriptionService.findById(id)));
        return "subscription/subscription-update-form";
    }

    @PutMapping("/{id}")
    public String updateSubscription(@PathVariable long id, UpdateSubscriptionDto subscriptionDto){
        subscriptionService.updateCostAndDateById(id, subscriptionDto.cost, subscriptionDto.date);
        return "redirect:/subscriptions";
    }

    @DeleteMapping
    public String deleteSubscription(@PathVariable long id){
        subscriptionService.deleteSubscription(id);
        return "redirect:/subscriptions";
    }

    @GetMapping
    public String getAllSubscriptions(Model model) {
        List<Subscription> allSubscriptions = subscriptionService.getSubscriptionsByUserId(1L);
        model.addAttribute("subscriptions", allSubscriptions);
        return "subscription/subscriptions";
    }

    @GetMapping("/my-subscriptions")//взять подписку пользователя
    public String getAllSubscriptionByUserId(Model model , HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");
       List<Subscription> allByUserId = subscriptionService.getSubscriptionsByUserId(userId);
       model.addAttribute("subscriptions", allByUserId);
       return "subscription/my-subscriptions";
    }

}
