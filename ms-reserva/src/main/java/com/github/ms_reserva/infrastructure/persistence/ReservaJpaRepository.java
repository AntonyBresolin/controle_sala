package com.github.ms_reserva.infrastructure.persistence;

import com.github.ms_reserva.domain.model.Reserva;
import com.github.ms_reserva.domain.repository.ReservaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaJpaRepository implements ReservaRepository {

    private final SpringDataReservaRepository repository;

    public ReservaJpaRepository(SpringDataReservaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Reserva> findAll() {
        return repository.findAll();
    }

    @Override
    public Reserva save(Reserva reserva) {
        return repository.save(reserva);
    }
}