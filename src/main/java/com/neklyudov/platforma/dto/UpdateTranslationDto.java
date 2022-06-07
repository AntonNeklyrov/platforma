package com.neklyudov.platforma.dto;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.Translation;
import com.neklyudov.platforma.model.User;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class UpdateTranslationDto {
    public Long id;
    public League league;
    public String guestTeam;
    public String homeTeam;
    public Date date;
    public Time time;
    public User user;
    public Commentator commentator;


    public static UpdateTranslationDto convert(Translation translation) {
        UpdateTranslationDto updateTranslationDto = new UpdateTranslationDto();
        updateTranslationDto.setId(translation.getId());
        updateTranslationDto.setLeague(translation.getLeague());
        updateTranslationDto.setGuestTeam(translation.getGuestTeam());
        updateTranslationDto.setHomeTeam(translation.getHomeTeam());
//        updateTranslationDto.setDate(translation.getDate());
//        updateTranslationDto.setTime(translation.getTime());
        updateTranslationDto.setUser(translation.getUser());
        updateTranslationDto.setCommentator(translation.getCommentator());
        return updateTranslationDto;
    }

}
