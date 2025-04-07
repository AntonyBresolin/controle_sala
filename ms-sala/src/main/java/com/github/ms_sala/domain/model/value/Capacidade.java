package com.github.ms_sala.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Capacidade {
    private int capacidade;

    protected Capacidade() {
    }

    public Capacidade(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
        }
        this.capacidade = capacidade;
    }
}
