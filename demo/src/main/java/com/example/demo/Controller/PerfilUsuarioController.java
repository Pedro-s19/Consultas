package com.example.demo.Controller;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Service.PerfilUsuarioServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfilusuarios")
public class PerfilUsuarioController {

    private final PerfilUsuarioServiceImp perfilUsuarioService;

    public PerfilUsuarioController(PerfilUsuarioServiceImp perfilUsuarioService) {
        this.perfilUsuarioService = perfilUsuarioService;
    }

    /*
     * Crea un nuevo perfil de usuario.
     * Llama a PerfilUsuarioService.save() que ejecuta un INSERT en SQL.
     */
    @PostMapping
    public ResponseEntity<PerfilUsuario> create(@RequestBody PerfilUsuario perfilUsuario)
    {
        PerfilUsuario saved =  perfilUsuarioService.save(perfilUsuario);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /*
     * Obtiene todos los perfiles.
     * Llama a PerfilUsuarioService.findAll() que ejecuta un SELECT *.
     */
    @GetMapping
    public List<PerfilUsuario> getAll()
    {
        return perfilUsuarioService.findAll();
    }
    /*
     * Obtiene un perfil por ID.
     * Llama a PerfilUsuarioService.findById() que ejecuta un SELECT con WHERE.
     */
    @GetMapping("{id}")
    public ResponseEntity<PerfilUsuario> getById(@PathVariable Long id)
    {
        return perfilUsuarioService.FindById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * Actualiza un perfil existente.
     * Verifica existencia (SELECT COUNT), asigna ID y llama a save() que ejecuta un UPDATE.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PerfilUsuario> update(@PathVariable Long id, @RequestBody PerfilUsuario perfilUsuario)
    {
        if(!perfilUsuarioService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        perfilUsuario.setId(id);
        PerfilUsuario updated = perfilUsuarioService.save(perfilUsuario);
        return ResponseEntity.ok(updated);

    }

    /*
     * Elimina un perfil por ID.
     * Llama a PerfilUsuarioService.deleteById() que ejecuta un DELETE.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if(!perfilUsuarioService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        perfilUsuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
