package com.codej.services.impl;

import com.codej.model.Image;
import com.codej.model.Proyecto;
import com.codej.repository.IImageRepository;
import com.codej.repository.IProyectoRepository;
import com.codej.services.IImagenService;
import com.codej.services.IProyectoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Proyecto save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void delete(Integer id) {
        proyectoRepository.deleteById(id);

    }

    @Override
    public Image guardar(Image image) {
        return imageRepository.save(image);
    }
}
