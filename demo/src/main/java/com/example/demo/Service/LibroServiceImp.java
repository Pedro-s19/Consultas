package com.example.demo.Service;

import com.example.demo.Model.Libro;
import com.example.demo.Repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibroServiceImp implements  LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImp(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    /*
     * Guarda un libro.
     * JPA: save()
     * Si el ID es nulo -> INSERT INTO libros (titulo, autor, anio_publicacion, categoria_id) VALUES (?, ?, ?, ?)
     * Si el ID existe -> UPDATE libros SET ... WHERE id = ?
     * @param libro objeto a guardar
     * @return libro guardado
     */
    @Override
    public Libro save (Libro libro)
    {
        return libroRepository.save(libro);
    }

    /*
     * Obtiene todos los libros.
     * JPA: findAll() -> SQL: SELECT * FROM libros
     * Debido al FetchType.EAGER en la relación con Categoria, se generará un JOIN o una consulta adicional
     * para traer la categoría de cada libro.
     * @return lista de libros
     */
    @Override
    public List<Libro> findAll()
    {
        return libroRepository.findAll();
    }

    /*
     * Busca un libro por ID.
     * JPA: findById(id) -> SQL: SELECT * FROM libros WHERE id = ?
     * Con EAGER, también se carga la categoría (posiblemente con un JOIN).
     * @param id identificador
     * @return Optional con el libro
     */
    @Override
    public Optional<Libro>findById(Long id)
    {
        return libroRepository.findById(id);
    }

    /*
     * Elimina un libro por ID.
     * JPA: deleteById(id) -> SQL: DELETE FROM libros WHERE id = ?
     * @param id identificador
     */
    @Override
    public void deleteById(Long id)
    {
        libroRepository.deleteById(id);
    }
    /*
     * Verifica si existe un libro con el ID dado.
     * JPA: existsById(id) -> SQL: SELECT COUNT(*) FROM libros WHERE id = ?
     * @param id identificador
     * @return true si existe
     */
    @Override
    public boolean existsById(Long id)
    {
        return libroRepository.existsById(id);
    }
}
