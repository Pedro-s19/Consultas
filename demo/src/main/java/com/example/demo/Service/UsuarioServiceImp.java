package com.example.demo.Service;

import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /*
     * Guarda un usuario.
     * JPA: usuarioRepository.save(usuario)
     * Si el ID es nulo -> SQL: INSERT INTO usuarios (nombre, corre) VALUES (?, ?)
     * Si el ID existe -> SQL: UPDATE usuarios SET nombre = ?, corre = ? WHERE id = ?
     */

    @Override
    public Usuario save(Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }

    /*
     * Obtiene todos los usuarios.
     *
     * JPA: usuarioRepository.findAll()
     * SQL: SELECT * FROM usuarios
     */
    @Override
    public List<Usuario> findAll()
    {
        return usuarioRepository.findAll();
    }

    /*
     * Busca un usuario por ID.
     * JPA: usuarioRepository.findById(id)
     * SQL: SELECT * FROM usuarios WHERE id = ?
     */
    @Override
    public Optional<Usuario> findById(Long id)
    {
        return usuarioRepository.findById(id);
    }

    /*
     * Elimina un usuario por ID.
     * JPA: usuarioRepository.deleteById(id)
     * SQL: DELETE FROM usuarios WHERE id = ?
     * (Como tiene cascade = ALL en la relación con PerfilUsuario, también se eliminará el perfil asociado,
     *  generando un DELETE en perfiles_usuario.)
     */
    @Override
    public void deleteById(Long id)
    {
        usuarioRepository.deleteById(id);
    }

    /*
     * Verifica si existe un usuario con el ID dado.
     * JPA: usuarioRepository.existsById(id)
     * SQL: SELECT COUNT(*) FROM usuarios WHERE id = ?
     */
    @Override
    public boolean existsById(Long id)
    {
        return usuarioRepository.existsById(id);
    }
}
