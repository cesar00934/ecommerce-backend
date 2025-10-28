package com.ecommerce.backend.dto;

public class NuevoProductoDto {
    private String nombre;
    private String descripcion;
    private Long categoria_id;
    private Integer stock;
    private Double precio_compra;
    private Double precio_venta;
    private String codigo_qr;
    private String codigo_barras;
    private String fecha_vencimiento; // ISO yyyy-MM-dd
    private String imagen_url;
    private String categoria_nombre;

    public NuevoProductoDto() {}

    public NuevoProductoDto(String nombre, String descripcion, Long categoria_id, Integer stock, Double precio_compra, Double precio_venta, String codigo_qr, String codigo_barras, String fecha_vencimiento, String imagen_url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria_id = categoria_id;
        this.stock = stock;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.codigo_qr = codigo_qr;
        this.codigo_barras = codigo_barras;
        this.fecha_vencimiento = fecha_vencimiento;
        this.imagen_url = imagen_url;
    }
    public String getCategoria_nombre() {
        return categoria_nombre;
    }

    public void setCategoria_nombre(String categoria_nombre) {
        this.categoria_nombre = categoria_nombre;
    }
    // Getters y Setters
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

    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(Double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getCodigo_qr() {
        return codigo_qr;
    }

    public void setCodigo_qr(String codigo_qr) {
        this.codigo_qr = codigo_qr;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }
}
