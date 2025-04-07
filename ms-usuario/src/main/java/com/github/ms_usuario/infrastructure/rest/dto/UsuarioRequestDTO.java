package com.github.ms_usuario.infrastructure.rest.dto;

import java.time.LocalDate;

public record UsuarioRequestDTO(String nome,
                                String email,
                                String cpf,
                                LocalDate dataNascimento) {
}
