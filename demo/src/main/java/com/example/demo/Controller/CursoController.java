package com.example.demo.Controller;

import com.example.demo.Model.Curso;
import com.example.demo.Service.CursoServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoServiceImp cursoService;

    public CursoController(CursoServiceImp cursoService) {
        this.cursoService = cursoService;
    }

    /*
     * Crea un nuevo curso.
     * Llama a CursoService.save() que ejecuta un INSERT en SQL.
     */
    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody Curso curso)
    {
        Curso saved =  cursoService.save(curso);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /*
     * Obtiene todos los cursos.
     * Llama a CursoService.findAll() que ejecuta un SELECT *.
     */
    @GetMapping
    public List<Curso> getAll()
    {
        return cursoService.findAll();
    }

    /*
     * Obtiene un curso por ID.
     * Llama a CursoService.findById() que ejecuta un SELECT con WHERE.
     */
    @GetMapping("{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id)
    {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * Actualiza un curso existente.
     * Verifica existencia (SELECT COUNT), asigna ID y llama a save() que ejecuta un UPDATE.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso)
    {
        if(!cursoService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        curso.setId(id);
        Curso updated = cursoService.save(curso);
        return ResponseEntity.ok(updated);

    }
    /*
     * Elimina un curso por ID.
     * Llama a CursoService.deleteById() que ejecuta un DELETE.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if(!cursoService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
