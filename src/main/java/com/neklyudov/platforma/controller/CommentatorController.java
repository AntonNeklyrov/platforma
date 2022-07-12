package com.neklyudov.platforma.controller;


import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.service.CommentatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/commentators")
public class CommentatorController {

    private final CommentatorService commentatorService;

    @Autowired
    public CommentatorController(CommentatorService commentatorService) {
        this.commentatorService = commentatorService;
    }

    @PostMapping
    public long createCommentator(@RequestBody Commentator commentator) {
        return commentatorService.addCommentator(commentator);
    }

    @PutMapping
    public void updateCommentator(@RequestBody Commentator commentator) {
        commentatorService.updateCommentator(commentator);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentator(@PathVariable long id){
        commentatorService.deleteCommentator(id);
    }

    @GetMapping("/{id}")
    public Commentator getCommentatorById(@PathVariable long id) {
        return commentatorService.getCommentatorById(id);
    }

    @GetMapping
    public List<Commentator> getCommentators() {
        return commentatorService.getAllCommentators();
    }
}
