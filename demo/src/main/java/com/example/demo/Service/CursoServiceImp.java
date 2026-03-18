package com.example.demo.Service;

import com.example.demo.Model.Curso;
import com.example.demo.Repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursoServiceImp implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImp(CursoRepository cursoRepository)
    {
        this.cursoRepository = cursoRepository;
    }

    /*
     * Guarda un curso.
     * JPA: cursoRepository.save(curso)
     * Si el ID es nulo -> SQL: INSERT INTO cursos (nombre, creditos) VALUES (?, ?)
     * Si el ID existe  -> SQL: UPDATE cursos SET nombre = ?, creditos = ? WHERE id =
     */
    @Override
    public Curso save (Curso curso)
    {
        return cursoRepository.save(curso);
    }

    /*
     * Obtiene todos los cursos.
     * JPA: cursoRepository.findAll()
     * SQL: SELECT * FROM cursos
     */
    @Override
    public List<Curso> findAll()
    {
        return cursoRepository.findAll();
    }

    /*
     * Busca un curso por ID.
     * JPA: cursoRepository.findById(id)
     * SQL: SELECT * FROM cursos WHERE id = ?
     */
    @Override
    public Optional<Curso> findById(Long id)
    {
        return cursoRepository.findById(id);
    }

    /*
     * Elimina un curso por ID.
     * JPA: cursoRepository.deleteById(id)
     * SQL: DELETE FROM cursos WHERE id = ?
     */
    @Override
    public void deleteById(Long id)
    {
        cursoRepository.deleteById(id);
    }

    /*
     * Verifica si existe un curso con el ID dado.
     * JPA: cursoRepository.existsById(id)
     * SQL: SELECT COUNT(*) FROM cursos WHERE id = ?
     */
    @Override
    public boolean existsById(Long id)
    {
        return cursoRepository.existsById(id);
    }


}
