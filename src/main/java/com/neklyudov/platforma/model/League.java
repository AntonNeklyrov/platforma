package com.neklyudov.platforma.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class League {
    private Long id;
    private String name;
}
