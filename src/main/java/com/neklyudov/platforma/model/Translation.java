package com.neklyudov.platforma.model;

import com.neklyudov.platforma.model.League;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Translation {
    private Long id;
    private League league;
    private String guestTeam;
    private String homeTeam;
    private String date;
    private String time;
    private Commentator commentator;
}
