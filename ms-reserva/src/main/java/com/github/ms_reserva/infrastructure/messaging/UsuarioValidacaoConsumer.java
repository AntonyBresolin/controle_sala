package com.github.ms_reserva.infrastructure.messaging;

import com.github.ms_reserva.application.UsuarioValidacaoService;
import com.github.ms_reserva.infrastructure.messaging.dto.UsuarioValidacaoMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidacaoConsumer {
    private final UsuarioValidacaoService usuarioValidacaoService;

    public UsuarioValidacaoConsumer(UsuarioValidacaoService usuarioValidacaoService) {
        this.usuarioValidacaoService = usuarioValidacaoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.usuario.validacao.response}")
    public void consumirResposta(UsuarioValidacaoMessage message){
        usuarioValidacaoService.receberResultadoValidacao(message);
    }
}
