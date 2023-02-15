package com.codej.controller;

import com.codej.model.Blog;
import com.codej.model.Etiqueta;
import com.codej.services.IBlogService;
import com.codej.services.IEtiquetaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BlogRestController {

    private final IBlogService blogService;
    private final IEtiquetaService etiquetaService;

    @GetMapping("/blog")
    public List<Blog> findAll() {
        return blogService.findAll();
    }

    @GetMapping("/blog/etiquetas")
    public List<Etiqueta> findAllByEtiquetas() {
        return etiquetaService.findAll();
    }



}
