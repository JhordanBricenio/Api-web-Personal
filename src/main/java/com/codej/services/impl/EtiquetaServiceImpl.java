package com.codej.services.impl;


import com.codej.model.Etiqueta;
import com.codej.repository.IEtiquetaRepository;
import com.codej.services.IEtiquetaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EtiquetaServiceImpl implements IEtiquetaService {

    private final IEtiquetaRepository etiquetaRepository;

    @Override
    public List<Etiqueta> findAll() {
        return etiquetaRepository.findAll();
    }

    @Override
    public Etiqueta findById(Integer id) {
        return etiquetaRepository.findById(id).orElse(null);
    }

    @Override
    public Etiqueta save(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    @Override
    public void delete(Integer id) {
        etiquetaRepository.deleteById(id);
    }
}
