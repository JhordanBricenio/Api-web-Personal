package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "El titulo no puede estar vacio")
    private String titulo;
    @Column(length = 65535, nullable = false) // especifica la longitud de la columna de descripción
    @NotBlank(message = "El contenido no puede estar vacia")
    private String contenido;
    private String imagen;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(nullable = false)
    @NotBlank(message = "El autor no puede estar vacio")
    private String autor;

    // Relacion con la tabla Etiqueta
    @JsonIgnoreProperties({"blog","hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "blog_etiqueta", joinColumns = @JoinColumn(name = "blog_id")
            , inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
    private List<Etiqueta> etiquetas;

    // Relacion con la tabla Usuario
   /*@JsonIgnoreProperties({"blog","hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;*/



}
