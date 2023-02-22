package com.codej.services.impl;

import com.codej.model.Image;
import com.codej.model.Proyecto;
import com.codej.repository.IImageRepository;
import com.codej.repository.IProyectoRepository;
import com.codej.services.IImagenService;
import com.codej.services.IProyectoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProyectoServiceImpl implements IProyectoService {

    private final IProyectoRepository proyectoRepository;
    private final IImageRepository imageRepository;

    @Override
    public List<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto findById(Integer id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(Proyecto proyecto) {

        Map<String, Object> response = new HashMap<>();
        Proyecto proyectoNew = null;
        try {
            proyectoNew = proyectoRepository.save(proyecto);
        }catch (Exception e){
            response.put("mensaje", "Error al crear el proyecto");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        response.put("mensaje", "El proyecto ha sido creado con éxito");
        response.put("proyecto", proyectoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public void delete(Integer id) {
        proyectoRepository.deleteById(id);

    }

    @Override
    public Page<Proyecto> findAll(Pageable pageable) {
        return proyectoRepository.findAll(pageable);
    }

    @Override
    public Page<Proyecto> findAllByFiltroPage(String filtro, Pageable pageable) {
        return proyectoRepository.findByNombreContaining(filtro, pageable);
    }

    @Override
    public Image guardar(Image image) {
        return imageRepository.save(image);
    }
}
