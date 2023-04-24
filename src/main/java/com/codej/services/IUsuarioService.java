package com.codej.services;

import com.codej.model.Usuario;
import org.springframework.http.ResponseEntity;


public interface IUsuarioService {
    public Usuario findById(Integer id);
    public ResponseEntity<?> save (Usuario usuario);
}
