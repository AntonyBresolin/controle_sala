package com.github.ms_usuario.infrastructure.persistence;

import com.github.ms_usuario.domain.model.Usuario;
import com.github.ms_usuario.domain.model.value.Cpf;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCpf(Cpf cpf);
}