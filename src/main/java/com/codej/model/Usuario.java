package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    private String nombres;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellidos;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "La clave no puede estar vacio")
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String telefono;
    private Boolean enabled=true;

    @Column(length = 200)
    private String foto;

    // Relacion con la tabla Rol
    @JsonIgnoreProperties({"usuario","hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id")
            , inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles;

    // Relacion con la tabla Blog
   /* @JsonIgnoreProperties({"usuario","hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Blog> blog;*/

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
