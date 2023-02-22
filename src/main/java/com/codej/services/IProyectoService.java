package com.codej.services;

import com.codej.model.Image;
import com.codej.model.Proyecto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProyectoService {
    public List<Proyecto> findAll();
    public Proyecto findById(Integer id);
    public ResponseEntity<?> save (Proyecto product);
    public void delete(Integer id);

    public Page<Proyecto> findAll(Pageable pageable);

    //Buscar por nombre y paginado
    public Page<Proyecto> findAllByFiltroPage(String filtro, Pageable pageable);

    public Image guardar(Image image);
}
