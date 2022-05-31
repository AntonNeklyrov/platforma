package com.neklyudov.platforma.repository.impl;

import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Translation;
import com.neklyudov.platforma.repository.TranslationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    @Override
    public long save(Translation translation) {

        return 0;
    }

    @Override
    public void update(Translation translation) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Translation> findAll() {
        return null;
    }

    @Override
    public List<League> findByLeagueId(long leagueId) {
        return null;
    }
}
