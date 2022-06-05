package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.dto.CreateSubscriptionDto;
import com.neklyudov.platforma.dto.CreateTranslationDto;
import com.neklyudov.platforma.dto.UpdateTranslationDto;
import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.Translation;
import com.neklyudov.platforma.service.CommentatorService;
import com.neklyudov.platforma.service.LeagueService;
import com.neklyudov.platforma.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/translations")
public class TranslationController {


    private final TranslationService translationService;
    private final CommentatorService commentatorService;
    private final LeagueService leagueService;

    @Autowired
    public TranslationController(TranslationService translationService, CommentatorService commentatorService, LeagueService leagueService) {
        this.translationService = translationService;
        this.commentatorService = commentatorService;
        this.leagueService = leagueService;
    }


    @PostMapping
    public String createTranslation(Model model,
                                    HttpSession httpSession,
                                    @ModelAttribute CreateTranslationDto translationDto) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        translationService.save(translationDto, 1L);
        return "redirect:/translations";
    }

    @GetMapping("/add")
    public String showTranslationForm(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        var translationDto = new CreateTranslationDto();
        model.addAttribute("translation", translationDto);
        model.addAttribute("leagues", leagueService.getAllLeagues());
        model.addAttribute("commentators", commentatorService.getAllCommentators());
        return "translation/translation-form";
    }



    @GetMapping("/update")
    public String showTranslationUpdateForm(Model model,
                                    @RequestParam long id,
                                    HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        model.addAttribute("updatedTranslation", UpdateTranslationDto.convert(translationService.findById(id)));
        model.addAttribute("commentators", commentatorService.getAllCommentators());
        model.addAttribute("leagues", leagueService.getAllLeagues());
        return "translation-update-form";
    }

//    @PutMapping("/{id}")
//    public String updateTranslation(@PathVariable long id, UpdateTranslationDto translationDto) {
//        translationService.updateTranslation(translationDto.);
//        return "redirect:/main";
//    }

    @DeleteMapping("/id")
    public String deleteTranslation(@PathVariable long id){
        translationService.deleteTranslation(id);
        return "redirect:/main";
    }

//    @GetMapping("/{id}")
//    public String getTranslation(@PathVariable long id, Model model, HttpSession session) {
//
//        Translation translation = translationService.findById(id);
//
//        if (commentatorId.isEmpty() && leagueId.isEmpty() || commentatorId.isPresent() && leagueId.isPresent()) {
//            throw new RuntimeException();
//        }
//
//        if (leagueId.isPresent()) {
//            return translationService.getTranslationsByLeagueId(leagueId.get());
//        }
//
//        return translationService.getTranslationsByCommentatorId(commentatorId.get());
//    }

    @GetMapping
    public String getAllTranslations(HttpSession httpSession, Model model) {
        var translations = translationService.getTranslationsByUserId(1L);
        model.addAttribute("translations", translations);
        return "translation/translations";
    }
}
