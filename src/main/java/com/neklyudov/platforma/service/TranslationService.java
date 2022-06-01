package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.Translation;

import java.util.List;

public interface TranslationService {

    long addTranslation(Translation translation);
    void updateTranslation(Translation translation);
    void deleteTranslation(long id);

    List<Translation> getTranslationsByLeagueId(long id);
    List<Translation> getTranslationsByCommentatorId(long id);

    List<Translation> getAllTranslations();
}
