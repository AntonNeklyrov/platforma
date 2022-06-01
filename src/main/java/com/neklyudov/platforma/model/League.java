package com.neklyudov.platforma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class League {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static LeagueBuilder builder() {
        return new LeagueBuilder();
    }

    public League(String name) {
        this.name = name;
    }

    public League(){
    }

    public static final class LeagueBuilder {
        private Long id;
        private String name;

        private LeagueBuilder() {
        }

        public LeagueBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LeagueBuilder name(String name) {
            this.name = name;
            return this;
        }

        public League build() {
            League league = new League();
            league.setId(id);
            league.setName(name);
            return league;
        }
    }
}
