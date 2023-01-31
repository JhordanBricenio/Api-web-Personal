package com.codej.services;

import com.codej.model.Image;
import com.codej.model.Proyecto;

import java.util.List;

public interface IProyectoService {
    public List<Proyecto> findAll();
    public Proyecto findById(Integer id);
    public Proyecto save (Proyecto product);
    public void delete(Integer id);

    public Image guardar(Image image);
}
