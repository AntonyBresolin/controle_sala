package com.github.ms_usuario.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Embeddable
@Setter
public class Nome {
    private String nome;

    protected Nome() {
    }

    public Nome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome deve ter pelo menos 3 caracteres.");
        }
        this.nome = nome;
    }
}
