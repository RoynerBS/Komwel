package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Inventario;
import com.komwel.Komwel.Models.InventarioShow;

import java.util.List;

public interface InventarioRepository {
    List<Inventario> getAll();
    List<InventarioShow> getAllShow();
    int create(Inventario inventario);

    int update(Inventario inventario);

    int deleteById(Long id);

    Inventario findById(Long id);
}
