package com.github.ms_reserva.infrastructure.messaging;

import com.github.ms_reserva.infrastructure.messaging.dto.UsuarioValidacaoMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidacaoProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String requestQueue;

    public UsuarioValidacaoProducer(RabbitTemplate rabbitTemplate,
                                    @Value("${rabbitmq.queue.usuario.validacao.request}") String requestQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.requestQueue = requestQueue;
    }

    public void enviarRequisicao(UsuarioValidacaoMessage message){
        rabbitTemplate.convertAndSend(requestQueue, message);
    }
}
