package com.komwel.Komwel.Models;

public class Dashboard {
    private long cantidad_usuarios;
    private long cantidad_productos;

    private long cantidad_categorias;
    private long cantidad_colecciones;
    public long getCantidad_usuarios() {
        return cantidad_usuarios;
    }

    public void setCantidad_usuarios(long cantidad_usuarios) {
        this.cantidad_usuarios = cantidad_usuarios;
    }

    public long getCantidad_productos() {
        return cantidad_productos;
    }

    public void setCantidad_productos(long cantidad_productos) {
        this.cantidad_productos = cantidad_productos;
    }

    public long getCantidad_categorias() {
        return cantidad_categorias;
    }

    public void setCantidad_categorias(long cantidad_categorias) {
        this.cantidad_categorias = cantidad_categorias;
    }

    public long getCantidad_colecciones() {
        return cantidad_colecciones;
    }

    public void setCantidad_colecciones(long cantidad_colecciones) {
        this.cantidad_colecciones = cantidad_colecciones;
    }


}
