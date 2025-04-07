package com.github.ms_reserva.infrastructure.rest.dto;

public record ReservaResponseDTO(Long id,
                                 String dataHora,
                                 Long salaId,
                                 Long usuarioId) {
}
