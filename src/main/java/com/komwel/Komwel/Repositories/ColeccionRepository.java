package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Coleccion;

import java.util.List;

public interface ColeccionRepository {

    List<Coleccion> getAll();
    int create(Coleccion coleccion);

    int update(Coleccion coleccion);

    int deleteById(Long id);

    Coleccion findById(Long id);
}
