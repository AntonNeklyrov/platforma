package com.neklyudov.platforma.service;

import com.neklyudov.platforma.model.Translation;

import java.util.List;

public interface TranslationService {

    long addTranslation(Translation translation);
    void updateTranslation(Translation translation);
    void deleteTranslation(long id);

    Translation getTranslationByLeagueId(long id);
    Translation getTranslationByCommentatorId(long ig);

    List<Translation> getAllTranslations();
}
