package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    Estudiante save(Estudiante estudiante);
    List<Estudiante> findAll();
    Optional<Estudiante> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
}
