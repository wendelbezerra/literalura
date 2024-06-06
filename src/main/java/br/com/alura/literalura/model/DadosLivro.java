package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro (
        @JsonAlias("title") String tituloLivro,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer totalDownloads ){}
