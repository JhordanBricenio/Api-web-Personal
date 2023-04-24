package com.codej.services.impl;

import com.codej.model.Proyecto;
import com.codej.model.Usuario;
import com.codej.repository.IUsuarioRepository;
import com.codej.services.IUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuarioNew = null;
        try {
            usuarioNew = usuarioRepository.save(usuario);
        }catch (Exception e){
            response.put("mensaje", "Error al crear el usuario");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        response.put("mensaje", "El usuario ha sido creado con éxito");
        response.put("usuario", usuarioNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
