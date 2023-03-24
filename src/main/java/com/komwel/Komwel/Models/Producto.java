package com.komwel.Komwel.Models;

public class Producto {

    private long id;
    private String nombre;
    private String descripcion;
    private long precio;
    private long id_categoria;

    private long id_coleccion;

    private long id_marca;

    private long id_talla;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public long getId_coleccion() {
        return id_coleccion;
    }

    public void setId_coleccion(long id_coleccion) {
        this.id_coleccion = id_coleccion;
    }
    public long getId_marca() {
        return id_marca;
    }

    public void setId_marca(long id_marca) {
        this.id_marca = id_marca;
    }

    public long getId_talla() {
        return id_talla;
    }

    public void setId_talla(long id_talla) {
        this.id_talla = id_talla;
    }
}
