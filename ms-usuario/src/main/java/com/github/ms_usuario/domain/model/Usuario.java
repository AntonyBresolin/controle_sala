package com.github.ms_usuario.domain.model;

import com.github.ms_usuario.domain.model.value.Cpf;
import com.github.ms_usuario.domain.model.value.DataNascimento;
import com.github.ms_usuario.domain.model.value.Email;
import com.github.ms_usuario.domain.model.value.Nome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Nome nome;
    @Embedded
    private Email email;
    @Embedded
    private Cpf cpf;
    @Embedded
    private DataNascimento dataNascimento;

    public Usuario(Nome nome, Email email, Cpf cpf, DataNascimento dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
}