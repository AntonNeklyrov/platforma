package com.neklyudov.platforma.controller;


import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.service.CommentatorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commentators")
public class CommentatorController {

    private final CommentatorService commentatorService;

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

    @DeleteMapping("/id")
    public void deleteCommentator(@PathVariable long id){
        commentatorService.deleteCommentator(id);
    }

    @GetMapping("/search")
    public Commentator getCommentatorById(@RequestParam Optional<Long> commentatorId) {
        if(commentatorId.isEmpty()){
            throw  new RuntimeException();
        }
        return commentatorService.getCommentatorById(commentatorId.get());
    }

    @GetMapping
    public List<Commentator> getCommentators() {
        return commentatorService.getAllCommentators();
    }
}
