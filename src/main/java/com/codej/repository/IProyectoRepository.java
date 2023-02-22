package com.codej.repository;

import com.codej.model.Proyecto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProyectoRepository extends JpaRepository<Proyecto, Integer> {
    Page<Proyecto> findByNombreContaining(String filtro, Pageable pageable);

}
