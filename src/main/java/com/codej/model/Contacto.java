package com.codej.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Column(nullable = false)
    @NotBlank(message = "El email no puede estar vacio")
    private String email;
    private String asunto;
    private String telefono;
    @Column(nullable = false)
    @NotBlank(message = "El mensaje no puede estar vacio")
    private String mensaje;
}
