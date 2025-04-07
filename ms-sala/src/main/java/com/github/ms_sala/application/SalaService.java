package com.github.ms_sala.application;

import com.github.ms_sala.domain.model.Sala;
import com.github.ms_sala.domain.repository.SalaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala criarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public Optional<Sala> buscarPorId(Long id) {
        return salaRepository.findById(id);
    }

    public List<Sala> listarTodas() {
        return salaRepository.findAll();
    }
}