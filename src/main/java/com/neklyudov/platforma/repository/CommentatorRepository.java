package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.Commentator;


import java.util.List;
import java.util.Optional;

public interface CommentatorRepository {
    long save(Commentator commentator);
    void update(Commentator commentator);
    void delete(long id);

    List<Commentator> findAll();
    Optional<Commentator> findById(long id);
}
