package com.github.ms_reserva.application;

import com.github.ms_reserva.domain.model.Reserva;
import com.github.ms_reserva.domain.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva salvar(Reserva reserva) {
        validarReserva(reserva);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    private void validarReserva(Reserva reserva) {
        if (reserva.getSalaId() == null || reserva.getUsuarioId() == null) {
            throw new IllegalArgumentException("Sala e Usuário são obrigatórios para a reserva.");
        }
        if (reserva.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A reserva não pode ser feita para uma data/hora no passado.");
        }
    }
}