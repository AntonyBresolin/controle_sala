package com.github.ms_sala.domain.repository;

import com.github.ms_sala.domain.model.Sala;

import java.util.List;
import java.util.Optional;

public interface SalaRepository {
    Optional<Sala> findById(Long id);
    List<Sala> findAll();
    Sala save(Sala sala);
}