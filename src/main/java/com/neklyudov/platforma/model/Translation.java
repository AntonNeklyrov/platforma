package com.neklyudov.platforma.model;

import com.neklyudov.platforma.model.League;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.Date;


@Data
@AllArgsConstructor
public class Translation {

    private Long id;
    private League league;
    private String guestTeam;
    private String homeTeam;
    private Date date;
    private Time time;
    private User user;
    private Commentator commentator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(String guestTeam) {
        this.guestTeam = guestTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commentator getCommentator() {
        return commentator;
    }

    public void setCommentator(Commentator commentator) {
        this.commentator = commentator;
    }

    public Translation(){
    }


    public static TranslationBuilder builder() {
        return new TranslationBuilder();
    }

    public static final class TranslationBuilder {

        private Long id;
        private League league;
        private String guestTeam;
        private String homeTeam;
        private Date date;
        private Time time;
        private User user;
        private Commentator commentator;

        private TranslationBuilder() {
        }


        public TranslationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TranslationBuilder league(League league) {
            this.league = league;
            return this;
        }

        public TranslationBuilder guestTeam(String guestTeam) {
            this.guestTeam = guestTeam;
            return this;
        }

        public TranslationBuilder homeTeam(String homeTeam) {
            this.homeTeam = homeTeam;
            return this;
        }

        public TranslationBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public TranslationBuilder time(Time time) {
            this.time = time;
            return this;
        }

        public TranslationBuilder user(User user) {
            this.user = user;
            return this;
        }

        public TranslationBuilder commentator(Commentator commentator) {
            this.commentator = commentator;
            return this;
        }

        public Translation build() {
            Translation translation = new Translation();
            translation.setId(id);
            translation.setLeague(league);
            translation.setGuestTeam(guestTeam);
            translation.setHomeTeam(homeTeam);
            translation.setDate(date);
            translation.setTime(time);
            translation.setUser(user);
            translation.setCommentator(commentator);
            return translation;
        }
    }
}
