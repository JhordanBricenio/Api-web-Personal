package com.codej.services;

import com.codej.model.Blog;
import com.codej.model.Image;
import com.codej.model.Proyecto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBlogService {
    public List<Blog> findAll();
    public Blog findById(Integer id);
    public Blog save (Blog blog);
    public void delete(Integer id);
    public Page<Blog> findAll(Pageable pageable);

}
