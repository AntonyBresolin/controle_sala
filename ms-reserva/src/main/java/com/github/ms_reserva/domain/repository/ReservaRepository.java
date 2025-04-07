package com.github.ms_reserva.domain.repository;

import com.github.ms_reserva.domain.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    Optional<Reserva> findById(Long id);
    List<Reserva> findAll();
    Reserva save(Reserva reserva);
}