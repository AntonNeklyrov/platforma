package com.neklyudov.platforma.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class League {

    private Long id;
    private String name;
    private String country;

    public League() {
    }

    public League(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}
