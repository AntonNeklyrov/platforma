package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.dto.CreateTranslationDto;
import com.neklyudov.platforma.dto.UpdateTranslationDto;
import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.Translation;
import com.neklyudov.platforma.service.CommentatorService;
import com.neklyudov.platforma.service.LeagueService;
import com.neklyudov.platforma.service.TranslationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/translations")
public class TranslationController {


    private final TranslationService translationService;
    private final CommentatorService commentatorService;
    private final LeagueService leagueService;

    public TranslationController(TranslationService translationService, CommentatorService commentatorService, LeagueService leagueService) {
        this.translationService = translationService;
        this.commentatorService = commentatorService;
        this.leagueService = leagueService;
    }

    @PostMapping
    public String createTranslation(Model model,
                                  @RequestBody Translation translation,
                                  HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        translationService.addTranslation(translation);
        return "redirect:/main";
    }

    @GetMapping("/update")
    public String showTranslationUpdateForm(Model model,
                                    @RequestParam long id,
                                    HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            model.addAttribute("forbiddenMessage", "Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь и повторите попытку.");
            return "forbidden";
        }
        model.addAttribute("updateTranslation",
                UpdateTranslationDto.convert(translationService.));
        model.addAttribute("commentators", commentatorService.getAllCommentators());
        model.addAttribute("leagues", leagueService.getAllLeagues());
        return "redirect:/main";
    }

    @PutMapping("/{id}")
    public String updateTranslation(@PathVariable long id, UpdateTranslationDto translationDto) {
        publicationService.updateHeaderAndContentById(id, publicationDto.header, publicationDto.content);
        return "redirect:/publications";
    }

    @DeleteMapping("/id")
    public String deleteTranslation(@PathVariable long id){
        translationService.deleteTranslation(id);
        return "redirect:/main";
    }

    @GetMapping("/search")
    public List<Translation> getSu(@RequestParam Optional<Long> commentatorId,
                                    @RequestParam Optional<Long>  leagueId) {
        if (commentatorId.isEmpty() && leagueId.isEmpty() || commentatorId.isPresent() && leagueId.isPresent()) {
            throw new RuntimeException();
        }

        if (leagueId.isPresent()) {
            return translationService.getTranslationsByLeagueId(leagueId.get());
        }

        return translationService.getTranslationsByCommentatorId(commentatorId.get());
    }

    @GetMapping
    public String getTranslations() {

        return translationService.getAllTranslations();
    }
}
