package com.github.ms_sala.infrastructure.persistence;

import com.github.ms_sala.domain.model.Sala;
import com.github.ms_sala.domain.repository.SalaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class SalaJpaRepository implements SalaRepository {

    private final SpringDataSalaRepository repository;

    public SalaJpaRepository(SpringDataSalaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Sala> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Sala> findAll() {
        return repository.findAll();
    }

    @Override
    public Sala save(Sala sala) {
        return repository.save(sala);
    }
}