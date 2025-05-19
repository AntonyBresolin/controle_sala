package com.github.ms_usuario.infrastructure.messaging;

import com.github.ms_usuario.infrastructure.messaging.dto.UsuarioValidacaoMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidacaoProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String responseQueue;

    public UsuarioValidacaoProducer(RabbitTemplate rabbitTemplate,
                                    @Value("${rabbitmq.queue.usuario.validacao.response}") String responseQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.responseQueue = responseQueue;
    }

    public void enviarResposta(UsuarioValidacaoMessage message) {
        rabbitTemplate.convertAndSend(responseQueue, message);
    }
}
