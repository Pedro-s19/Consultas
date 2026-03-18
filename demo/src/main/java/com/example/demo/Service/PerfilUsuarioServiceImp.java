package com.example.demo.Service;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.PerfilUsuarioRepository;
import com.example.demo.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PerfilUsuarioServiceImp implements PerfilUsuarioService {

    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    public PerfilUsuarioServiceImp(PerfilUsuarioRepository perfilUsuarioRepository, UsuarioRepository usuarioRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /*
     * Guarda un perfil de usuario.
     * JPA: perfilUsuarioRepository.save(perfil)
     * Si el ID es nulo -> SQL: INSERT INTO perfiles_usuario (documento, telefono, usuario_id) VALUES (?, ?, ?)
     * Si el ID existe  -> SQL: UPDATE perfiles_usuario SET documento = ?, telefono = ?, usuario_id = ? WHERE id = ?
     */
    @Override
    public PerfilUsuario save(PerfilUsuario perfilUsuario) {

        return perfilUsuarioRepository.save(perfilUsuario);
    }
    @Override
    public PerfilUsuario saveUsuario (Long id,PerfilUsuario perfilUsuario)
    {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
         perfilUsuario.setUsuario(usuario);

        return perfilUsuarioRepository.save(perfilUsuario);
    }

    /*
     * Obtiene todos los perfiles.
     * JPA: perfilUsuarioRepository.findAll()
     * SQL: SELECT * FROM perfiles_usuario
     */
    @Override
    public List<PerfilUsuario> findAll()
    {
        return perfilUsuarioRepository.findAll();
    }

    /*
     * Busca un perfil por ID.
     * JPA: perfilUsuarioRepository.findById(id)
     * SQL: SELECT * FROM perfiles_usuario WHERE id = ?
     */
    @Override
    public Optional<PerfilUsuario> findById(Long id)
    {
        return perfilUsuarioRepository.findById(id);
    }

    /*
     * Elimina un perfil por ID.
     * JPA: perfilUsuarioRepository.deleteById(id)
     * SQL: DELETE FROM perfiles_usuario WHERE id = ?
     */
    @Override
    public void deleteById(Long id)
    {
        perfilUsuarioRepository.deleteById(id);
    }

    /*
     * Verifica si existe un perfil con el ID dado.
     * JPA: perfilUsuarioRepository.existsById(id)
     * SQL: SELECT COUNT(*) FROM perfiles_usuario WHERE id = ?
     */
    @Override
    public boolean existsById(Long id)
    {
        return perfilUsuarioRepository.existsById(id);
    }
}
