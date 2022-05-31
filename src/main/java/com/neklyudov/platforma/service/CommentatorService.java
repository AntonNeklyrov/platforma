package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.User;

import java.util.List;

public interface CommentatorService {
    long addCommentator(Commentator commentator);
    void updateCommentator(Commentator commentator);
    void deleteCommentator(long id);

    Commentator getCommentatorById(long id);

    List<Commentator> getAllCommentator();
}
