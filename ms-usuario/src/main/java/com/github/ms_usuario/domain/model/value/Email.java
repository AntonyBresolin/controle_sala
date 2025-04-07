package com.github.ms_usuario.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Embeddable
@Setter
public class Email {
    private String email;

    protected Email() {
    }

    public Email(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.email = email;
    }
}