package com.aluracursos.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto (
        @JsonAlias({"title"})
        String title,
        @JsonAlias({"authors"})
        List<AuthorDto> authorList,
        @JsonAlias({"languages"})
        List<String> languajes,
        @JsonAlias({"download_count"})
        Integer downloads) {
}
