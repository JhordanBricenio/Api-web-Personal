package com.codej.services;

import com.codej.model.Blog;
import com.codej.model.Image;

import java.util.List;

public interface IBlogService {
    public List<Blog> findAll();
    public Blog findById(Integer id);
    public Blog save (Blog blog);
    public void delete(Integer id);

    public Image guardar(Image image);
}
