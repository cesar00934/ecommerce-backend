package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
// CategoryRepository.java
import java.util.Optional;
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByNombreIgnoreCase(String nombre);
}

