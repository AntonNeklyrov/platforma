package com.neklyudov.platforma.dto;

import com.neklyudov.platforma.model.Commentator;
import com.neklyudov.platforma.model.League;
import com.neklyudov.platforma.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTranslationDto {

    public Long leagueId;

    public String guestTeam;

    public String homeTeam;

    public String date;

    public String time;

    public Long commentatorId;
}
