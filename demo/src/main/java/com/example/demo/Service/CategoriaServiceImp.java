package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaServiceImp implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository) {

        this.categoriaRepository = categoriaRepository;
    }
    /*
     * Guarda una categoria nueva o actualizada.
     * JPA: save()
     * Si el ID es nulo:  INSERT INTO categorias(nombre, descripcion) VALUES( , )
     * Si el ID existe:   UPDATE categorias SET nombre= " ", descripcion=" " WHERE id= ?
     * @param categoria objeto a guardar
     * @return la categoria guardada (con ID generado si es nueva)
     */

    @Override
    public Categoria save(Categoria categoria)
    {
        return categoriaRepository.save(categoria);
    }
    /*
     *Obtiene todas las categorías.
     *JPA: findAll() -> SQL: SELECT * FROM categorias
     *@return lista de categorías
     */

    @Override
    public List<Categoria> findAll()
    {
        return categoriaRepository.findAll();
    }
    /*
     * Busca una categoría por su ID.
     * JPA: findById(id) -> SQL: SELECT * FROM categorias WHERE id = ?
     * @param id identificador
     * @return Optional con la categoría si existe
     */
    @Override
    public Optional<Categoria> findById(Long id)
    {
        return categoriaRepository.findById(id);
    }
    /*
     * Elimina una categoría por su ID.
     * JPA: deleteById(id) -> SQL: DELETE FROM categorias WHERE id = ?
     * @param id identificador
     */
    @Override
    public void deleteById(Long id)
    {
        categoriaRepository.deleteById(id);
    }
    /*
     * Verifica si existe una categoría con el ID dado.
     * JPA: existsById(id) -> SQL: SELECT COUNT(*) FROM categorias WHERE id = ?
     * @param id identificador
     * @return true si existe
     */
    @Override
    public boolean existsById(Long id)
    {
        return categoriaRepository.existsById(id);
    }
}
