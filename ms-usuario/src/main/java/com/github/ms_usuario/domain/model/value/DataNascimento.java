package com.github.ms_usuario.domain.model.value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Embeddable
public class DataNascimento {
    private LocalDate dataNascimento;

    protected DataNascimento() {}

    public DataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @JsonCreator
    public DataNascimento(@JsonProperty("dataNascimento") String dataNascimento) {
        this.dataNascimento = LocalDate.parse(dataNascimento);
    }
}
