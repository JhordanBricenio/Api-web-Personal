package com.codej.controller;

import com.codej.model.Blog;
import com.codej.model.Etiqueta;
import com.codej.model.Proyecto;
import com.codej.services.IBlogService;
import com.codej.services.IEtiquetaService;
import com.codej.services.IUploadService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BlogRestController {

    private final IBlogService blogService;
    private final IEtiquetaService etiquetaService;

    private final IUploadService uploadService;

    @GetMapping("/blog")
    public List<Blog> findAll() {
        return blogService.findAll();
    }

    @GetMapping("/blog/page/{page}")
    public Page<Blog> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return blogService.findAll(pageable);
    }
    @PostMapping("/blog")
    public Blog save(@RequestBody Blog blog) {
        return blogService.save(blog);
    }

    @GetMapping("/blog/{id}")
    public Blog findById(@PathVariable Integer id) {
        return blogService.findById(id);
    }

    @GetMapping("/blog/etiquetas")
    public List<Etiqueta> findAllByEtiquetas() {
        return etiquetaService.findAll();
    }

    @PostMapping("/blog/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile archivo, @RequestParam("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        Blog blog = blogService.findById(id);
        if(!archivo.isEmpty()){
            String nombreArchivo= null;
            try {
                nombreArchivo= uploadService.copiar(archivo);
            }catch (IOException e){
                response.put("mensaje", "Error al subir la imagen del producto ");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnt= blog.getImagen();
            uploadService.eliminar(nombreFotoAnt);
            blog.setImagen(nombreArchivo);
            blogService.save(blog);
            response.put("blog", blog);
            response.put("mensaje", "Has subido corectamente la imagen"+nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }




}
