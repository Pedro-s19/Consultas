package com.example.demo.Service;

import com.example.demo.Model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);


}
