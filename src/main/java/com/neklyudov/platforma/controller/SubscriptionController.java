package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.dto.CreateSubscriptionDto;
import com.neklyudov.platforma.dto.UpdateSubscriptionDto;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.SubscriptionPage;
import com.neklyudov.platforma.service.LeagueService;
import com.neklyudov.platforma.service.SubscriptionService;
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


    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, LeagueService leagueService) {
        this.subscriptionService = subscriptionService;
        this.leagueService = leagueService;

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

    @PostMapping("/add-to-user/{id}")
    public String addSubscriptionToUser(HttpSession httpSession, @PathVariable long  id){
        Subscription subscription =  subscriptionService.findById(id);
        CreateSubscriptionDto subscriptionDto = new CreateSubscriptionDto();
        Long sessionUserId = (Long) httpSession.getAttribute("userId");
        subscriptionDto.period = subscription.getPeriod();
        subscriptionDto.cost = subscription.getCost();
        subscriptionDto.leagueId = subscription.getLeague().getId();
        subscriptionDto.leagueName = subscription.getLeague().getName();
        subscriptionService.save(subscriptionDto,sessionUserId);
        return "redirect:/subscriptions/my-subscriptions";
    }

    @GetMapping("/update/{id}")
    public String showSubscriptionUpdateForm(Model model,  HttpSession httpSession, @PathVariable long id) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        model.addAttribute("updateSubscription", UpdateSubscriptionDto.convert(subscriptionService.findById(id)));
        return "subscription/subscription-update-form";
    }

    @PutMapping("/{id}")
    public String updateSubscription(@PathVariable long id, @ModelAttribute UpdateSubscriptionDto subscriptionDto){
        subscriptionService.updateCostAndPeriodById(id, subscriptionDto.cost, subscriptionDto.period);
        return "redirect:/subscriptions";
    }

    @PostMapping("/delete/{id}")
    public String deleteSubscription(@PathVariable Long id){
        subscriptionService.deleteSubscription(id);
        return "redirect:/subscriptions";
    }

    @GetMapping
    public String getAllSubscriptions(Model model,
                                      HttpSession httpSession,
                                      @RequestParam(defaultValue = "1") Integer page) {
        SubscriptionPage subscriptionPage = subscriptionService.findPage(page);
        model.addAttribute("subscriptions", subscriptionPage.getSubscriptions());
        model.addAttribute("currentPage", page);
        model.addAttribute("pagesCount", subscriptionPage.getRowsCount());
        return "subscription/subscriptions";
    }

    @GetMapping("/my-subscriptions")
    public String getAllSubscriptionByUserId(Model model , HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        Long userId = (Long) httpSession.getAttribute("userId");
       List<Subscription> allByUserId = subscriptionService.getSubscriptionsByUserId(userId);
       model.addAttribute("subscriptions", allByUserId);
       return "subscription/my-subscriptions";
    }

}
