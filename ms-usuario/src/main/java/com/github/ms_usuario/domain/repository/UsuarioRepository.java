package com.github.ms_usuario.domain.repository;

import com.github.ms_usuario.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> findByCpf(String cpf);
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    boolean existsById(Long id);
}