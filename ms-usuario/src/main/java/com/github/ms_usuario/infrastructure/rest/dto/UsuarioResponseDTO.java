package com.github.ms_usuario.infrastructure.rest.dto;

import com.github.ms_usuario.domain.model.value.Endereco;

import java.time.LocalDate;

public record UsuarioResponseDTO(Long id,
                                 String nome,
                                 String email,
                                 String cpf,
                                 LocalDate dataNascimento,
                                 Endereco endereco) {
}
