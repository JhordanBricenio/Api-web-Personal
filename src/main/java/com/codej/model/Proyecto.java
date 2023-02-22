package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false, length= 3000 )
    private String descripcion;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(nullable = false)
    private String estado;
    private String foto;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String demo;

    // Relacion con la tabla image
    @JsonIgnoreProperties({"proyecto","hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "proyecto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> imagenes;

}
