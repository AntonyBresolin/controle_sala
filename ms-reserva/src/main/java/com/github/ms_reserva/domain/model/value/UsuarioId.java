package com.github.ms_reserva.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class UsuarioId {
    private Long usuarioId;

    protected UsuarioId() {
    }

    public UsuarioId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        this.usuarioId = id;
    }
}
