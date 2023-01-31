package com.codej.repository;

import com.codej.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository <Image, Integer> {
}
