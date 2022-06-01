package com.neklyudov.platforma.dto;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.User;
import lombok.Data;
import lombok.NonNull;

import java.sql.Time;
import java.util.Date;

@Data
public class CreateTranslationDto {
    @NonNull
    public League league;
    @NonNull
    public String guestTeam;
    @NonNull
    public String homeTeam;
    @NonNull
    public Date date;
    @NonNull
    public Time time;
    public Commentator commentator;
}
