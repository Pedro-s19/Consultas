package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = " cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true,length = 100)
    private String nombre;

    @Column(nullable = false)
    private Integer creditos;

    @ManyToMany(mappedBy = "cursos")
    @JsonIgnoreProperties("cursos")
    private Set<Estudiante>estudiantes = new HashSet<>();

    public Curso() {
    }

    public Curso(Long id, String nombre, Integer creditos, Set<Estudiante> estudiantes) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.estudiantes = estudiantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", estudiantes=" + estudiantes +
                '}';
    }
}
