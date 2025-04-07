package com.github.ms_reserva.infrastructure.rest;

import com.github.ms_reserva.application.ReservaService;
import com.github.ms_reserva.domain.model.Reserva;
import com.github.ms_reserva.infrastructure.rest.dto.ReservaRequestDTO;
import com.github.ms_reserva.infrastructure.rest.dto.ReservaResponseDTO;
import com.github.ms_reserva.infrastructure.rest.mapper.ReservaMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listar() {
        List<ReservaResponseDTO> reservas = reservaService.listar()
                .stream()
                .map(ReservaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(reservas);
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> salvar(@RequestBody ReservaRequestDTO reservaRequestDTO) {
        Reserva reserva = ReservaMapper.toEntity(reservaRequestDTO);
        Reserva saved = reservaService.salvar(reserva);
        return ResponseEntity.ok(ReservaMapper.toDTO(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> buscarPorId(@PathVariable Long id) {
        return reservaService.buscarPorId(id)
                .map(ReservaMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}