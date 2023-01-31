package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombres;
    private String apellidos;
    private String password;
    private String email;
    private String dni;
    private String telefono;
    private Boolean enabled=true;

    // Relacion con la tabla Rol
    @JsonIgnoreProperties({"usuario","hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id")
            , inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles;

    // Relacion con la tabla Blog
    @JsonIgnoreProperties({"usuario","hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Blog> blog;

    public void  agregarRol(Rol rol){
        if (roles == null){
            roles = new LinkedList<Rol>();
        }
        roles.add(rol);
    }

    public Usuario() {
        this.roles= new ArrayList<>();
    }
}
