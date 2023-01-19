package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descripcion;
    private String contenido;
    private String imagen;

    @Temporal(TemporalType.DATE)
    private String fecha;
    private String autor;

    // Relacion con la tabla Etiqueta
    @JsonIgnoreProperties({"blog","hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "blog_etiqueta", joinColumns = @JoinColumn(name = "blog_id")
            , inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
    private List<Etiqueta> etiquetas;

    // Relacion con la tabla Usuario
    @JsonIgnoreProperties({"blog","hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;



}
