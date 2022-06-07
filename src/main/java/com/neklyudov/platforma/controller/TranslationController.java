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
        var commentators = commentatorService.getAllCommentators();
        System.out.println(commentators);
        model.addAttribute("commentators", commentators);
        return "translation/translation-form";
    }

    @GetMapping("/watch/{id}")
    public String watchTranslation(@PathVariable Long id, HttpSession httpSession, Model model){
        var translation = translationService.findById(id);
        model.addAttribute("translation", translation);
        return "translation/translation_page";
    }


    @GetMapping("/update/{id}")
    public String showTranslationUpdateForm(Model model,
                                            HttpSession httpSession,
                                            @PathVariable Long id) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        model.addAttribute("updatedTranslation", UpdateTranslationDto.convert(translationService.findById(id)));
        model.addAttribute("commentators", commentatorService.getAllCommentators());
        return "translation/translation-update-form";
    }

    @PutMapping("/{id}")
    public String updateTranslation(@PathVariable long id, UpdateTranslationDto translationDto) {
        translationService.updateTime(id, translationDto.time);
        return "redirect:/main";
    }

    @DeleteMapping("/id")
    public String deleteTranslation(@PathVariable long id){
        translationService.deleteTranslation(id);
        return "redirect:/main";
    }

    @GetMapping("/my-translations")
    public String showMyTranslations(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        Long sessionUserId = (Long) httpSession.getAttribute("userId");
        var translations = translationService.getTranslationsByUserId(sessionUserId);
        model.addAttribute("translations", translations);
        return "translation/my-translations";

    }

    @GetMapping
    public String getAllTranslations(HttpSession httpSession, Model model) {
        var translations = translationService.getTranslationsByUserId(1L);
        model.addAttribute("translations", translations);
        return "translation/translations";
    }
}
