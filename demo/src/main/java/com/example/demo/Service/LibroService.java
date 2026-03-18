package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    Libro save(Libro libro);
    List<Libro> findAll();
    Optional<Libro> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
}
