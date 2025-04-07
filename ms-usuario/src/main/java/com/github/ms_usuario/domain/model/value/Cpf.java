package com.github.ms_usuario.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter
@Embeddable
@Setter
public class Cpf {
    private String cpf;

    protected Cpf() {
    }

    public Cpf(String cpf) {
        if (!Pattern.matches("\\d{11}", cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }
}
