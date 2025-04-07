package com.github.ms_reserva.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class DataHora {
    private LocalDateTime dataHora;

    protected DataHora() {
    }

    public DataHora(LocalDateTime dataHora) {
        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data da reserva não pode estar no passado.");
        }
        this.dataHora = dataHora;
    }

    public boolean isBefore(LocalDateTime outraData) {
        return this.dataHora.isBefore(outraData);
    }

}