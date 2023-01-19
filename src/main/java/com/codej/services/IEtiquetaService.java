package com.codej.services;

import com.codej.model.Etiqueta;

import java.util.List;

public interface IEtiquetaService {
    public List<Etiqueta> findAll();
    public Etiqueta findById(Integer id);
    public Etiqueta save (Etiqueta etiqueta);
    public void delete(Integer id);
}
