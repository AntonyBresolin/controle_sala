package com.github.ms_reserva.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class SalaId {
    private Long salaId;

    protected SalaId() {
    }

    public SalaId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID da sala inválido.");
        }
        this.salaId = id;
    }
}
