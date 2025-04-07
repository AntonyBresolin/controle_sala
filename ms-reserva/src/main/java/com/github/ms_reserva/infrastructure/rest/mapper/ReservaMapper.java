package com.github.ms_reserva.infrastructure.rest.mapper;

import com.github.ms_reserva.domain.model.Reserva;
import com.github.ms_reserva.domain.model.value.*;
import com.github.ms_reserva.infrastructure.rest.dto.ReservaRequestDTO;
import com.github.ms_reserva.infrastructure.rest.dto.ReservaResponseDTO;

import java.time.LocalDateTime;

public class ReservaMapper {

    public static Reserva toEntity(ReservaRequestDTO dto) {
        return new Reserva(
                new DataHora(LocalDateTime.parse(dto.dataHora())),
                new SalaId(dto.salaId()),
                new UsuarioId(dto.usuarioId())
        );
    }

    public static ReservaResponseDTO toDTO(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getDataHora().getDataHora().toString(),
                reserva.getSalaId().getSalaId(),
                reserva.getUsuarioId().getUsuarioId()
        );
    }
}
