package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Producto;
import java.util.List;
public interface ProductoRepository {


    int create(Producto producto);

    int update(Producto producto);

    int deleteById(Long id);
    Producto findById(Long id);
    List<Producto> getAll();





}
