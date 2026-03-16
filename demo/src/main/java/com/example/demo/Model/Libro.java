package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 120)
    private String titulo;
    @Column(nullable = false, length = 100)
    private String autor;
    @Column(name = "anio-publicacion",nullable = false)
    private int anioPublicacion;

    //OPTIONA - LIBROS SIEMPRE DEBEN TENER CATEGORIAS
    //FETCH EAGER - CUANDO SE CARGA UN LIBRO TAMBIEN SE CARGA LA CATEGORIA

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn( name = "categoria_id", nullable = false)
    @JsonBackReference
    private Categoria categoria;

    public Libro() {

    }

    public Libro(Long id, String titulo, String autor, int anioPublicacion, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", categoria=" + categoria +
                '}';
    }
}
