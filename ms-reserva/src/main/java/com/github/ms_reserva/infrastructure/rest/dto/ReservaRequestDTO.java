package com.github.ms_reserva.infrastructure.rest.dto;

public record ReservaRequestDTO(String dataHora,
                                Long salaId,
                                Long usuarioId) {
}
