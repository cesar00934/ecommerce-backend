package com.ecommerce.backend.model;


import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public Category() {}

    public Category(String nombre) {
        this.nombre = nombre;
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
}






