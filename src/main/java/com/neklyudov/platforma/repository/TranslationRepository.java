package com.neklyudov.platforma.repository;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.model.Translation;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface TranslationRepository {
    long save(Translation translation);
    void update(Translation translation);
    void delete(Long id);

    List<Translation> findAll();
    List<Translation> findByLeagueId(Long leagueId);
    List<Translation> findByCommentatorId(Long commentatorId);
    Optional<Translation> findById(Long Id);
    List<Translation> findAllByUserId(Long userId);
    void updateTime(Long id, Time time);

}
