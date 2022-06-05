package com.neklyudov.platforma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class League {

    private Long id;
    private String name;

    public League() {
    }

    public League(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
