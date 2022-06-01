package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.Translation;

import java.util.List;

public interface TranslationRepository {
    long save(Translation translation);
    void update(Translation translation);
    void delete(long id);

    List<Translation> findAll();
    List<Translation> findByLeagueId(long leagueId);
    List<Translation> findByCommentatorId(long commentatorId);
}
