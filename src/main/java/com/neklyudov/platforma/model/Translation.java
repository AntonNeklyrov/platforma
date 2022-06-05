package com.neklyudov.platforma.model;

import com.neklyudov.platforma.model.League;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.Date;


@Data
@Builder
public class Translation {

    private Long id;
    private League league;
    private String guestTeam;
    private String homeTeam;
    private Date date;
    private Time time;
    private User user;
    private Commentator commentator;

    public Translation() {
    }

    public Translation(Long id, League league, String guestTeam, String homeTeam, Date date, Time time, User user, Commentator commentator) {
        this.id = id;
        this.league = league;
        this.guestTeam = guestTeam;
        this.homeTeam = homeTeam;
        this.date = date;
        this.time = time;
        this.user = user;
        this.commentator = commentator;
    }
}
