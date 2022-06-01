package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.Translation;
import com.neklyudov.platforma.repository.TranslationRepository;
import com.neklyudov.platforma.service.TranslationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;

    public TranslationServiceImpl(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public long addTranslation(Translation translation) {
        return translationRepository.save(translation);
    }

    @Override
    public void updateTranslation(Translation translation) {
        translationRepository.update(translation);
    }

    @Override
    public void deleteTranslation(long id) {
        translationRepository.delete(id);
    }

    @Override
    public List<Translation> getTranslationsByLeagueId(long id) {
        return translationRepository.findByLeagueId(id);
    }

    @Override
    public List<Translation> getTranslationsByCommentatorId(long id) {
        return translationRepository.findByCommentatorId(id);
    }

    @Override
    public List<Translation> getAllTranslations() {

        return translationRepository.findAll();
    }
}
