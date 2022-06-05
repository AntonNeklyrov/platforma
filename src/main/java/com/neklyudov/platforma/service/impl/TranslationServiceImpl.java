package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.dto.CreateSubscriptionDto;
import com.neklyudov.platforma.dto.CreateTranslationDto;
import com.neklyudov.platforma.model.*;
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
    public void addTranslation(Translation translation) {
        translationRepository.save(translation);
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
    public Translation findById(long id) {
        return translationRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
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

    @Override
    public Long save(CreateTranslationDto translationDto, Long userId) {
        Translation translation = Translation.builder()
                .homeTeam(translationDto.getHomeTeam())
                .guestTeam(translationDto.getGuestTeam())
                .date(translationDto.getDate())
                .time(translationDto.getTime())
                .league(League.builder().id(translationDto.getLeagueId()).build())
                .user(User.builder().id(userId).build())
                .commentator(Commentator.builder().id(translationDto.getCommentatorId()).build())
                .build();
        return translationRepository.save(translation);
    }

    @Override
    public List<Translation> getTranslationsByUserId(Long userId) {
        return translationRepository.findAllByUserId(userId);
    }
}
