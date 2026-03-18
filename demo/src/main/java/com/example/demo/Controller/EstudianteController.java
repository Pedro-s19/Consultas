package com.example.demo.Controller;

import com.example.demo.Model.Estudiante;
import com.example.demo.Service.EstudianteServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteServiceImp estudianteService;

    public EstudianteController(EstudianteServiceImp estudianteService) {
        this.estudianteService = estudianteService;
    }

    /*
     * Crea un nuevo estudiante.
     * Llama a EstudianteService.save() que ejecuta un INSERT en SQL.
     * Si se incluyen cursos en la relación ManyToMany, se generan INSERTS adicionales en la tabla intermedia estudiante_curso.
     */
    @PostMapping
    public ResponseEntity<Estudiante> create(@RequestBody Estudiante estudiante)
    {
        Estudiante saved =  estudianteService.save(estudiante);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /*
     * Obtiene todos los estudiantes.
     * Llama a EstudianteService.findAll() que ejecuta un SELECT * FROM estudiantes.
     */
    @GetMapping
    public List<Estudiante> getAll()
    {
        return estudianteService.findAll();
    }

    /*
     * Obtiene un estudiante por ID.
     * Llama a EstudianteService.findById() que ejecuta un SELECT * FROM estudiantes WHERE id = ?.
     * Al acceder a la colección de cursos (lazy), se generará una consulta adicional a la tabla intermedia.
     */
    @GetMapping("{id}")
    public ResponseEntity<Estudiante> getById(@PathVariable Long id)
    {
        return estudianteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * Actualiza un estudiante existente.
     * Verifica existencia (SELECT COUNT), asigna ID y llama a save() que ejecuta un UPDATE.
     * Si se modifican los cursos, se actualiza la tabla intermedia (primero borra relaciones antiguas y luego inserta las nuevas).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id, @RequestBody Estudiante estudiante)
    {
        if(!estudianteService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        estudiante.setId(id);
        Estudiante updated = estudianteService.save(estudiante);
        return ResponseEntity.ok(updated);

    }

    /*
     * Elimina un estudiante por ID.
     * Llama a EstudianteService.deleteById() que ejecuta un DELETE FROM estudiantes WHERE id = ?.
     * Las relaciones en la tabla intermedia se eliminan automáticamente si la BD tiene ON DELETE CASCADE, de lo contrario JPA las elimina una a una.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if(!estudianteService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        estudianteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
