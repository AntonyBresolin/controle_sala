package com.github.ms_usuario.infrastructure.persistence;

import com.github.ms_usuario.domain.model.Usuario;
import com.github.ms_usuario.domain.model.value.Cpf;
import com.github.ms_usuario.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioJpaRepository implements UsuarioRepository {

    private final SpringDataUsuarioRepository repository;

    public UsuarioJpaRepository(SpringDataUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Usuario> findByCpf(String cpf) {
        Cpf cpfValue = new Cpf(cpf);
        return repository.findByCpf(cpfValue);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<Usuario> findAll(){
        return repository.findAll();
    }
}