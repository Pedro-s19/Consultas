package com.example.demo.Controller;

import com.example.demo.Model.Libro;
import com.example.demo.Service.LibroServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    private final LibroServiceImp libroService;

    public LibrosController(LibroServiceImp libroService) {
        this.libroService = libroService;
    }

    /*
     * Crea un nuevo libro.
     * Llama a LibroService.save() que ejecuta un INSERT en SQL.
     */
    @PostMapping
    public ResponseEntity<Libro> create(@RequestBody Libro libro)
    {
        Libro saved =  libroService.save(libro);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /*
     * Obtiene todos los libros.
     * Llama a LibroService.findAll() que ejecuta un SELECT con JOIN.
     */
    @GetMapping
    public List<Libro> getAll()
    {
        return libroService.findAll();
    }

    /*
     * Obtiene un libro por ID.
     * Llama a LibroService.findById() que ejecuta un SELECT con WHERE y JOIN.
     */
    @GetMapping("{id}")
    public ResponseEntity<Libro> getById(@PathVariable Long id)
    {
        return libroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * Actualiza un libro existente.
     * Verifica existencia (SELECT COUNT), asigna ID y llama a save() que ejecuta un UPDATE.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro)
    {
        if(!libroService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        libro.setId(id);
        Libro updated = libroService.save(libro);
        return ResponseEntity.ok(updated);

    }

    /*
     * Elimina un libro por ID.
     * Llama a LibroService.deleteById() que ejecuta un DELETE.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if(!libroService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
