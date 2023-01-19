package com.codej.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String asunto;
    private String telefono;
    private String mensaje;
}
