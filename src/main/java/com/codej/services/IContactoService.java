package com.codej.services;

import com.codej.model.Contacto;
import com.codej.model.Proyecto;

import java.util.List;

public interface IContactoService {
    public List<Contacto> findAll();
    public Contacto findById(Integer id);
    public Contacto save (Contacto product);
    public void delete(Integer id);
}
