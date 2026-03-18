package com.example.demo.Service;

import com.example.demo.Model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario save(Usuario  usuario);
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
}
