package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Model.PerfilUsuario;

import java.util.List;
import java.util.Optional;

public interface PerfilUsuarioService {

    PerfilUsuario saveUsuario(Long id,PerfilUsuario perfilUsuario);
    PerfilUsuario save(PerfilUsuario perfilUsuario);
    List<PerfilUsuario> findAll();
    Optional<PerfilUsuario> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
}
