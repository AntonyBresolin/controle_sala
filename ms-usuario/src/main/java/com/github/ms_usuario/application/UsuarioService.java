package com.github.ms_usuario.application;

import com.github.ms_usuario.domain.model.Usuario;
import com.github.ms_usuario.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }

    public Optional<Usuario> buscarPorCpf (String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    public List<Usuario> buscarTodos(){return usuarioRepository.findAll();}
}