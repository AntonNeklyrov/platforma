package com.neklyudov.platforma.dto;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTranslationDto {
    @NonNull
    public Long leagueId;
    @NonNull
    public String guestTeam;
    @NonNull
    public String homeTeam;
    @NonNull
    public Date date;
    @NonNull
    public Time time;

    public Long commentatorId;
}
