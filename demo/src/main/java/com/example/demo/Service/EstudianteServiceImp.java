package com.example.demo.Service;

import com.example.demo.Model.Estudiante;
import com.example.demo.Repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudianteServiceImp implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImp(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    /*
     * Guarda un estudiante.
     * JPA: estudianteRepository.save(estudiante)
     * Si el ID es nulo -> SQL: INSERT INTO estudiantes (nombre, correo) VALUES (?, ?)
     * Si el ID existe -> SQL: UPDATE estudiantes SET nombre = ?, correo = ? WHERE id = ?
     * Si además se incluyen cursos en la relación ManyToMany, se generan INSERTS en la tabla intermedia.
     */
    @Override
    public Estudiante save (Estudiante estudiante)
    {
        return estudianteRepository.save(estudiante);
    }

    /*
     * Obtiene todos los estudiantes.
     * JPA: estudianteRepository.findAll()
     * SQL: SELECT * FROM estudiantes
     */
    @Override
    public List<Estudiante> findAll()
    {
        return estudianteRepository.findAll();
    }

    /*
     * Busca un estudiante por ID.
     * JPA: estudianteRepository.findById(id)
     * SQL: SELECT * FROM estudiantes WHERE id = ?
     */
    @Override
    public Optional<Estudiante> findById(Long id)
    {
        return estudianteRepository.findById(id);
    }

    /*
     * Elimina un estudiante por ID.
     * JPA: estudianteRepository.deleteById(id)
     * SQL: DELETE FROM estudiantes WHERE id = ?
     * (Si hay registros en la tabla intermedia, se eliminan automáticamente si la BD tiene ON DELETE CASCADE,
     *  de lo contrario JPA los elimina uno a uno.)
     */
    @Override
    public void deleteById(Long id)
    {
        estudianteRepository.deleteById(id);
    }

    /*
     * Verifica si existe un estudiante con el ID dado.
     * JPA: estudianteRepository.existsById(id)
     * SQL: SELECT COUNT(*) FROM estudiantes WHERE id = ?
     */
    @Override
    public boolean existsById(Long id)
    {
        return estudianteRepository.existsById(id);
    }
}
