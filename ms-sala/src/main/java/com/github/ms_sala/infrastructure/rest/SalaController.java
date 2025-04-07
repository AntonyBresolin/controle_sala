package com.github.ms_sala.infrastructure.rest;

import com.github.ms_sala.application.SalaService;
import com.github.ms_sala.domain.model.Sala;
import com.github.ms_sala.infrastructure.dto.SalaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity<SalaResponseDTO> criarSala(@RequestBody Sala sala) {
        Sala novaSala = salaService.criarSala(sala);
        return ResponseEntity.ok(toDTO(novaSala));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Sala> sala = salaService.buscarPorId(id);
        return sala.map(s -> ResponseEntity.ok(toDTO(s)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> listarTodas() {
        List<SalaResponseDTO> salasDTO = salaService.listarTodas()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(salasDTO);
    }

    private SalaResponseDTO toDTO(Sala sala) {
        return new SalaResponseDTO(
                sala.getId(),
                sala.getNome().getNome(),
                sala.getCapacidade().getCapacidade()
        );
    }
}
