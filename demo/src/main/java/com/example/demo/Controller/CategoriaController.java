package com.example.demo.Controller;

import com.example.demo.Model.Categoria;
import com.example.demo.Service.CategoriaServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaServiceImp categoriaService;

    public CategoriaController(CategoriaServiceImp categoriaService) {
        this.categoriaService = categoriaService;
    }


    /*
     * Crea una nueva categoría.
     * @param categoria datos en JSON (sin id, sin libros o con libros si se desea crear en cascada)
     * @return la categoría creada con código 201
     */
    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria)
    {
        Categoria save = categoriaService.save(categoria);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
    /*
     * Obtiene todas las categorías.
     * @return lista de categorías
     */
    @GetMapping
    public List<Categoria>getAll()
    {
        return categoriaService.findAll();
    }

    /*
     * Obtiene una categoría por su ID.
     * @param id identificador
     * @return categoría si existe, o 404 si no
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categoria>getById(@PathVariable Long id)
    {
        return categoriaService.findById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /*
     * Actualiza una categoría existente.
     * @param id identificador de la categoría a actualizar
     * @param categoria datos nuevos (puede incluir libros para reemplazar la lista)
     * @return categoría actualizada, o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria)
    {
        if(!categoriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        Categoria update = categoriaService.save(categoria);
        return ResponseEntity.ok(update);
    }
    /*
     * Elimina una categoría por su ID.
     * @param id identificador
     * @return 204 No Content si se eliminó, o 404 si no existe
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if(!categoriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
