package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

  @Autowired CategoryRepository categoryRepository;

  @GetMapping
  public List<Category> listAll(){ return categoryRepository.findAll(); }

  @PostMapping
  public Category create(@RequestBody Category c){
    return categoryRepository.save(c);
  }
}
