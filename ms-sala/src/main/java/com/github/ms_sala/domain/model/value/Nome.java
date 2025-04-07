package com.github.ms_sala.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Nome {
    private String nome;

    protected Nome() {
    }

    public Nome(String nome) {
        if (nome == null || nome.length() < 3) {
            throw new IllegalArgumentException("Nome da sala inválido!");
        }
        this.nome = nome;
    }
}

