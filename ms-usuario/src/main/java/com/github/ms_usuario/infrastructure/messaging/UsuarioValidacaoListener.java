package com.github.ms_usuario.infrastructure.messaging;

import com.github.ms_usuario.application.UsuarioService;
import com.github.ms_usuario.infrastructure.messaging.dto.UsuarioValidacaoMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidacaoListener {

    private final UsuarioService usuarioService;
    private final RabbitTemplate rabbitTemplate;
    private final String responseQueue;

    public UsuarioValidacaoListener(
            UsuarioService usuarioService,
            RabbitTemplate rabbitTemplate,
            @Value("${rabbitmq.queue.usuario.validacao.response}") String responseQueue) {
        this.usuarioService = usuarioService;
        this.rabbitTemplate = rabbitTemplate;
        this.responseQueue = responseQueue;
    }

    @RabbitListener(queues = "${rabbitmq.queue.usuario.validacao.request}")
    public void validarUsuario(UsuarioValidacaoMessage message) {
        boolean usuarioExiste = usuarioService.existePorId(message.getUsuarioId());

        UsuarioValidacaoMessage response = new UsuarioValidacaoMessage(message.getUsuarioId(), usuarioExiste);
        rabbitTemplate.convertAndSend(responseQueue, response);
    }
}