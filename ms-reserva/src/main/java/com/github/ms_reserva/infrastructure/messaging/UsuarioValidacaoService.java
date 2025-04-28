package com.github.ms_reserva.infrastructure.messaging;

import com.github.ms_reserva.infrastructure.messaging.dto.UsuarioValidacaoMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UsuarioValidacaoService {

    private final RabbitTemplate rabbitTemplate;
    private final String requestQueue;
    private final ConcurrentHashMap<Long, CompletableFuture<Boolean>> pendingValidations = new ConcurrentHashMap<>();

    public UsuarioValidacaoService(
            RabbitTemplate rabbitTemplate,
            @Value("${rabbitmq.queue.usuario.validacao.request}") String requestQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.requestQueue = requestQueue;
    }

    public CompletableFuture<Boolean> validarUsuario(Long usuarioId) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        pendingValidations.put(usuarioId, future);

        rabbitTemplate.convertAndSend(requestQueue, new UsuarioValidacaoMessage(usuarioId, false));

        return future;
    }

    public void receberResultadoValidacao(UsuarioValidacaoMessage message) {
        CompletableFuture<Boolean> future = pendingValidations.remove(message.getUsuarioId());
        if (future != null) {
            future.complete(message.isValido());
        }
    }
}