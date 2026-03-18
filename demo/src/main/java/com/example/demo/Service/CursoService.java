package com.example.demo.Service;

import com.example.demo.Model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    Curso save(Curso curso);
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
}
