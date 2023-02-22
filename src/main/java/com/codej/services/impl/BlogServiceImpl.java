package com.codej.services.impl;

import com.codej.model.Blog;
import com.codej.model.Image;
import com.codej.model.Proyecto;
import com.codej.repository.IBlogRepository;
import com.codej.services.IBlogService;
import com.codej.services.IUploadService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements IBlogService {

    private final IBlogRepository blogRepository;
    private final IUploadService uploadService;
    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Integer id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void delete(Integer id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
       return blogRepository.findAll(pageable);
    }


}
