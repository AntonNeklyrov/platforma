package com.neklyudov.platforma.service;

import com.neklyudov.platforma.dto.CreateSubscriptionDto;
import com.neklyudov.platforma.dto.CreateTranslationDto;
import com.neklyudov.platforma.dto.UpdateTranslationDto;
import com.neklyudov.platforma.model.Translation;

import java.sql.Time;
import java.util.List;

public interface TranslationService {

    void addTranslation(Translation translation);
    void updateTranslation(Translation translation);
    void deleteTranslation(long id);

    Translation findById(long id);
    List<Translation> getTranslationsByLeagueId(long id);
    List<Translation> getTranslationsByCommentatorId(long id);

    List<Translation> getAllTranslations();

    Long save(CreateTranslationDto translationDto, Long translationId);
    List<Translation> getTranslationsByUserId(Long userId);

    void updateTime(Long id, Time time);
}
