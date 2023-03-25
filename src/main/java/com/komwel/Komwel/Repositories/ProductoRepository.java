package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Producto;
import com.komwel.Komwel.Models.ProductoShow;

import java.util.List;
public interface ProductoRepository {


    int create(Producto producto);

    int update(Producto producto);

    int deleteById(Long id);
    Producto findById(Long id);
    Producto findByName(String name);
    List<Producto> getAll();
    int verificar(Producto producto);
    int verificarDep(Producto producto);
    List<ProductoShow> getAllShow();





}
