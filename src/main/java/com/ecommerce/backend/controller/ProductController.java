package com.ecommerce.backend.controller;


import com.ecommerce.backend.model.*;
import com.ecommerce.backend.dto.NuevoProductoDto;
import com.ecommerce.backend.repository.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

  @Autowired ProductRepository productRepository;
  @Autowired CategoryRepository categoryRepository;

@GetMapping
public List<Product> listAll() {
    return productRepository.findAllWithCategory();
}


  @PostMapping
  public Product create(@RequestBody NuevoProductoDto dto) {
    Product p = new Product();
    p.setNombre(dto.getNombre());
    p.setDescripcion(dto.getDescripcion());
    if(dto.getCategoria_id() != null){
      categoryRepository.findById(dto.getCategoria_id()).ifPresent(p::setCategoria);
    }
    p.setStock(dto.getStock() != null ? dto.getStock() : 0);
    p.setPrecioCompra(dto.getPrecio_compra() != null ? dto.getPrecio_compra() : 0.0);
    p.setPrecioVenta(dto.getPrecio_venta());
    p.setCodigoQR(dto.getCodigo_qr());
    p.setCodigoBarras(dto.getCodigo_barras());
    if(dto.getFecha_vencimiento() != null && !dto.getFecha_vencimiento().isEmpty()){
      p.setFechaVencimiento(LocalDate.parse(dto.getFecha_vencimiento()));
    }
    p.setImagenUrl(dto.getImagen_url());
    return productRepository.save(p);
  }

@PutMapping("/{id}")
public Product update(@PathVariable Long id, @RequestBody NuevoProductoDto dto) {
    Product p = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    // actualizar campos normales
    p.setNombre(dto.getNombre());
    p.setDescripcion(dto.getDescripcion());
    p.setStock(dto.getStock() != null ? dto.getStock() : 0);
    p.setPrecioCompra(dto.getPrecio_compra() != null ? dto.getPrecio_compra() : 0.0);
    p.setPrecioVenta(dto.getPrecio_venta() != null ? dto.getPrecio_venta() : 0.0);
    p.setCodigoQR(dto.getCodigo_qr());
    p.setCodigoBarras(dto.getCodigo_barras());
    if (dto.getFecha_vencimiento() != null && !dto.getFecha_vencimiento().isEmpty()) {
        p.setFechaVencimiento(LocalDate.parse(dto.getFecha_vencimiento()));
    } else {
        p.setFechaVencimiento(null);
    }




    // Lógica de categoría corregida
if (dto.getCategoria_id() != null) {
    // Esta parte ahora solo se ejecuta si se envía un ID explícito (ej: desde un <select>).
    // En nuestro caso, será false porque el frontend envió null.
    // ...
} else if (dto.getCategoria_nombre() != null && !dto.getCategoria_nombre().trim().isEmpty()) {
    // ¡Esta condición ahora será verdadera!
    // El backend tomará "Bebidas", buscará si existe una categoría con ese nombre.
    // Si no existe, la creará, y luego la asignará al producto.
    String nombreCategoria = dto.getCategoria_nombre().trim();
    Category cat = categoryRepository.findByNombreIgnoreCase(nombreCategoria)
        .orElseGet(() -> {
            Category newCat = new Category();
            newCat.setNombre(nombreCategoria);
            return categoryRepository.save(newCat);
        });
    p.setCategoria(cat);
} else {
    // Si el nombre de la categoría está vacío, se quita la categoría.
    p.setCategoria(null);
}



    // imagen (si vino en dto)
    if (dto.getImagen_url() != null) {
        p.setImagenUrl(dto.getImagen_url());
    }

    return productRepository.save(p);
}


@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
  productRepository.findById(id).ifPresent(p -> productRepository.deleteById(id));
  return ResponseEntity.noContent().build();
}


}
