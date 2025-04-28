package com.github.ms_usuario.infrastructure.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioValidacaoMessage {
    private Long usuarioId;
    private boolean valido;
}