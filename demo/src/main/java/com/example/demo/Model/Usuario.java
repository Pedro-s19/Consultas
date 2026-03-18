package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 80)
    private String nombre;
    @Column(nullable = false,unique = true,length = 120)
    private String corre;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usuario")
    private PerfilUsuario perfil;

    public Usuario() {
    }

    public Usuario(Long usuarioId) {
    }

    public Usuario(Long id, String nombre, String corre, PerfilUsuario perfil) {
        this.id = id;
        this.nombre = nombre;
        this.corre = corre;
        this.perfil = perfil;
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

    public String getCorre() {
        return corre;
    }

    public void setCorre(String corre) {
        this.corre = corre;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + id +
                ", nombre='" + nombre + '\'' +
                ", corre='" + corre + '\'' +
                ", perfil=" + perfil +
                '}';
    }
}
