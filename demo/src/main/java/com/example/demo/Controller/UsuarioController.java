package com.example.demo.Controller;

import com.example.demo.Model.Usuario;
import com.example.demo.Service.UsuarioServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImp usuarioService;

    public UsuarioController(UsuarioServiceImp usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*
     * Crea un nuevo usuario.
     * Llama a UsuarioService.save() que ejecuta un INSERT en SQL.
     */
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario)
    {
        Usuario saved =  usuarioService.save(usuario);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /*
     * Obtiene todos los usuarios.
     * Llama a UsuarioService.findAll() que ejecuta un SELECT *.
     */
    @GetMapping
    public List<Usuario> getAll()
    {
        return usuarioService.findAll();
    }

    /*
     * Obtiene un usuario por ID.
     * Llama a UsuarioService.findById() que ejecuta un SELECT con WHERE.
     */
    @GetMapping("{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id)
    {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * Actualiza un usuario existente.
     * Verifica existencia (SELECT COUNT), asigna ID y llama a save() que ejecuta un UPDATE.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario)
    {
        if(!usuarioService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        Usuario updated = usuarioService.save(usuario);
        return ResponseEntity.ok(updated);

    }

    /*
     * Elimina un usuario por ID.
     * Llama a UsuarioService.deleteById() que ejecuta un DELETE.
     * Como tiene cascade = ALL en la relación con PerfilUsuario, también se eliminará el perfil asociado,
     * generando un DELETE en perfiles_usuario.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if(!usuarioService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

