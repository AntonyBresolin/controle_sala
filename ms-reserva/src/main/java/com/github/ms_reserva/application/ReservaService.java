package com.github.ms_reserva.application;

import com.github.ms_reserva.domain.model.Reserva;
import com.github.ms_reserva.domain.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final UsuarioValidacaoService usuarioValidacaoService;

    public ReservaService(ReservaRepository reservaRepository, UsuarioValidacaoService usuarioValidacaoService) {
        this.reservaRepository = reservaRepository;
        this.usuarioValidacaoService = usuarioValidacaoService;
    }

    public Reserva salvar(Reserva reserva) {
        validarReserva(reserva);

        try {
            boolean usuarioValido = usuarioValidacaoService
                    .validarUsuario(reserva.getUsuarioId().getUsuarioId())
                    .get(5, TimeUnit.SECONDS);

            if (!usuarioValido) {
                throw new IllegalArgumentException("Usuário não encontrado");
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException("Erro ao validar usuário", e);
        }

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