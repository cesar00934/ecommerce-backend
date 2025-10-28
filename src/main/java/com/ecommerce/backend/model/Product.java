package com.ecommerce.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "products")
public class Product {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(columnDefinition = "text")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Category categoria;

    private Integer stock;
    private Double precioCompra;
    private Double precioVenta;
    private String codigoQR;
    private String codigoBarras;
    private LocalDate fechaVencimiento;
    private String imagenUrl;

    public Product() {}

    public Product(String nombre, String descripcion, Category categoria, Integer stock, Double precioCompra, Double precioVenta, String codigoQR, String codigoBarras, LocalDate fechaVencimiento, String imagenUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.codigoQR = codigoQR;
        this.codigoBarras = codigoBarras;
        this.fechaVencimiento = fechaVencimiento;
        this.imagenUrl = imagenUrl;
    }
@JsonProperty("categoria_nombre")
public String getCategoriaNombre() {
    return this.categoria != null ? this.categoria.getNombre() : null;
}
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}