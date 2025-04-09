package com.github.ms_usuario.domain.model.value;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Embeddable
@Setter
public class Endereco {
    private String cidade;
    private String estado;
    private String cep;
    private String rua;

    protected Endereco() {
    }

    public Endereco(String cidade, String estado, String cep, String rua) {
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.rua = rua;
    }
}
