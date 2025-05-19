package com.github.ms_usuario.infrastructure.messaging;

import com.github.ms_usuario.application.UsuarioService;
import com.github.ms_usuario.infrastructure.messaging.dto.UsuarioValidacaoMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidacaoConsumer {
    private final UsuarioService usuarioService;
    private final UsuarioValidacaoProducer usuarioValidacaoProducer;

    public UsuarioValidacaoConsumer(UsuarioService usuarioService, UsuarioValidacaoProducer usuarioValidacaoProducer) {
        this.usuarioService = usuarioService;
        this.usuarioValidacaoProducer = usuarioValidacaoProducer;
    }
    @RabbitListener(queues = "${rabbitmq.queue.usuario.validacao.request}")
    public void consumer(UsuarioValidacaoMessage message) {
        boolean usuarioExiste = usuarioService.existePorId(message.getUsuarioId());

        UsuarioValidacaoMessage response = new UsuarioValidacaoMessage(message.getUsuarioId(), usuarioExiste);
        usuarioValidacaoProducer.enviarResposta(response);
    }
}
