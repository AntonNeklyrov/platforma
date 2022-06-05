package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.repository.CommentatorRepository;
import com.neklyudov.platforma.service.CommentatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentatorServiceImpl implements CommentatorService {

    private final CommentatorRepository commentatorRepository;

    public CommentatorServiceImpl(CommentatorRepository commentatorRepository) {
        this.commentatorRepository = commentatorRepository;
    }

    @Override
    public long addCommentator(Commentator commentator) {
        return commentatorRepository.save(commentator);
    }

    @Override
    public void updateCommentator(Commentator commentator) {
        commentatorRepository.update(commentator);
    }

    @Override
    public void deleteCommentator(long id) {
        commentatorRepository.delete(id);
    }

    @Override
    public Commentator getCommentatorById(long id) {
        return commentatorRepository.findById(id).orElseThrow(()->new RuntimeException("Commentator not found"));
    }

    @Override
    public List<Commentator> getAllCommentators() {
        return commentatorRepository.findAll();
    }
}
