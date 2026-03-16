package com.example.demo.Service;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Repository.PerfilUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PerfilUsuarioServiceImp {

    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public PerfilUsuarioServiceImp(PerfilUsuarioRepository perfilUsuarioRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
    }

    /*
     * Guarda un perfil de usuario.
     * JPA: perfilUsuarioRepository.save(perfil)
     * Si el ID es nulo -> SQL: INSERT INTO perfiles_usuario (documento, telefono, usuario_id) VALUES (?, ?, ?)
     * Si el ID existe  -> SQL: UPDATE perfiles_usuario SET documento = ?, telefono = ?, usuario_id = ? WHERE id = ?
     */
    public PerfilUsuario save (PerfilUsuario perfilUsuario)
    {
        return perfilUsuarioRepository.save(perfilUsuario);
    }

    /*
     * Obtiene todos los perfiles.
     * JPA: perfilUsuarioRepository.findAll()
     * SQL: SELECT * FROM perfiles_usuario
     */
    public List<PerfilUsuario> findAll()
    {
        return perfilUsuarioRepository.findAll();
    }

    /*
     * Busca un perfil por ID.
     * JPA: perfilUsuarioRepository.findById(id)
     * SQL: SELECT * FROM perfiles_usuario WHERE id = ?
     */
    public Optional<PerfilUsuario> FindById(Long id)
    {
        return perfilUsuarioRepository.findById(id);
    }

    /*
     * Elimina un perfil por ID.
     * JPA: perfilUsuarioRepository.deleteById(id)
     * SQL: DELETE FROM perfiles_usuario WHERE id = ?
     */
    public void deleteById(Long id)
    {
        perfilUsuarioRepository.deleteById(id);
    }

    /*
     * Verifica si existe un perfil con el ID dado.
     * JPA: perfilUsuarioRepository.existsById(id)
     * SQL: SELECT COUNT(*) FROM perfiles_usuario WHERE id = ?
     */
    public boolean existsById(Long id)
    {
        return perfilUsuarioRepository.existsById(id);
    }
}
