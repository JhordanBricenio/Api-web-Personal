package com.codej.services.impl;

import com.codej.model.Contacto;
import com.codej.repository.IContactoRepository;
import com.codej.services.IContactoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactoServiceImpl implements IContactoService {

    private final IContactoRepository contactoRepository;

    @Override
    public List<Contacto> findAll() {
        return contactoRepository.findAll();
    }

    @Override
    public Contacto findById(Integer id) {
        return contactoRepository.findById(id).orElse(null);
    }

    @Override
    public Contacto save(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    @Override
    public void delete(Integer id) {
        contactoRepository.deleteById(id);
    }
}
