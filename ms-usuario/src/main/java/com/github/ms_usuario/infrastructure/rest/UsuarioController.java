package com.github.ms_usuario.infrastructure.rest;

import com.github.ms_usuario.application.UsuarioService;
import com.github.ms_usuario.domain.model.Usuario;
import com.github.ms_usuario.domain.model.value.*;
import com.github.ms_usuario.infrastructure.rest.dto.UsuarioRequestDTO;
import com.github.ms_usuario.infrastructure.rest.dto.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioRequestDTO userDTO) {
        Usuario usuario = new Usuario(
                new Nome(userDTO.nome()),
                new Email(userDTO.email()),
                new Cpf(userDTO.cpf()),
                new DataNascimento(userDTO.dataNascimento()),
                new Endereco(userDTO.endereco().getCidade(), userDTO.endereco().getEstado(), userDTO.endereco().getCep(), userDTO.endereco().getRua())
        );
        return ResponseEntity.ok(usuarioService.criarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodos();

        List<UsuarioResponseDTO> responseDTOs = usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome().getNome(),
                        usuario.getEmail().getEmail(),
                        usuario.getCpf().getCpf(),
                        usuario.getDataNascimento().getDataNascimento(),
                        usuario.getEndereco()
                ))
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorCpf(@PathVariable String cpf) {
        return usuarioService.buscarPorCpf(cpf)
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome().getNome(),
                        usuario.getEmail().getEmail(),
                        usuario.getCpf().getCpf(),
                        usuario.getDataNascimento().getDataNascimento(),
                        usuario.getEndereco()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}