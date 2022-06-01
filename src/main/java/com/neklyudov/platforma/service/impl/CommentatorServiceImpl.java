package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.service.CommentatorService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentatorServiceImpl implements CommentatorService {

    private final CommentatorService commentatorService;

    public CommentatorServiceImpl(@Lazy CommentatorService commentatorService) {
        super();
        this.commentatorService = commentatorService;
    }

    @Override
    public long addCommentator(Commentator commentator) {
        return commentatorService.addCommentator(commentator);
    }

    @Override
    public void updateCommentator(Commentator commentator) {
        commentatorService.updateCommentator(commentator);
    }

    @Override
    public void deleteCommentator(long id) {
        commentatorService.deleteCommentator(id);
    }

    @Override
    public Commentator getCommentatorById(long id) {
        return commentatorService.getCommentatorById(id);
    }

    @Override
    public List<Commentator> getAllCommentators() {
        return commentatorService.getAllCommentators();
    }
}
