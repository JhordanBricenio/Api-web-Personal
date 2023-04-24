package com.codej.controller;

import com.codej.model.Proyecto;
import com.codej.model.Usuario;
import com.codej.services.IUploadService;
import com.codej.services.IUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UsuarioRestController {

    private final IUsuarioService usuarioService;
    private final IUploadService uploadService;

    @GetMapping("/usuario/{id}")
    public Usuario show(@PathVariable Integer id){
        return usuarioService.findById(id);
    }

    @PostMapping("/usuario/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile archivo,
                                    @RequestParam("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = usuarioService.findById(id);
        if(!archivo.isEmpty()){
            String nombreArchivo= null;
            try {
                nombreArchivo= uploadService.copiar(archivo);
            }catch (IOException e){
                response.put("mensaje", "Error al subir la imagen del usuario");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnt= usuario.getFoto();
            uploadService.eliminar(nombreFotoAnt);
            usuario.setFoto(nombreArchivo);
            usuarioService.save(usuario);
            response.put("usuario", usuario);
            response.put("mensaje", "Has subido corectamente la imagen"+nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }



}
