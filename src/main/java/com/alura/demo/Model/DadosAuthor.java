package com.alura.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAuthor(
        @JsonProperty("birth_year") Integer birthYear,
        @JsonProperty("death_year") Integer deathYear,
        @JsonProperty("name") String name
) {
}