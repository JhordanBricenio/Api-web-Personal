package com.codej.controller;

import com.codej.model.Etiqueta;
import com.codej.services.IEtiquetaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EtiquetaRestController {
    private final IEtiquetaService etiquetaService;

    @GetMapping("/etiqueta")
    public List<Etiqueta> findAll(){
        return etiquetaService.findAll();
    }
    @PostMapping("/etiqueta")
    public Etiqueta save(@RequestBody Etiqueta etiqueta){
        return etiquetaService.save(etiqueta);
    }
    @GetMapping("/etiqueta/{id}")
    public Etiqueta getId(@PathVariable Integer id){
        return etiquetaService.findById(id);
    }
    @PutMapping("/etiqueta/{id}")
    public Etiqueta update(@PathVariable Integer id, @RequestBody Etiqueta etiqueta){
        Etiqueta etiquetaNew= etiquetaService.findById(id);
        etiquetaNew.setNombre(etiqueta.getNombre());
        etiquetaNew.setColor(etiqueta.getColor());
        return etiquetaService.save(etiquetaNew);
    }
    @DeleteMapping("/etiqueta/{id}")
    public void delete(@PathVariable Integer id){
        etiquetaService.delete(id);
    }
}
