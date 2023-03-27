package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/leagues")
public class LeagueController {
    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("/add")
    public String showLeagueForm(Model model,
                                 HttpSession httpSession) {
        model.addAttribute("league", new League());
        return  "subscription/league-form";
    }

    @PostMapping
    public String createLeague(@ModelAttribute League league) {
        leagueService.addLeague(league);
        return "subscription/subscriptions";
    }

}
