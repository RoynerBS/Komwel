package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Marca;

import java.util.List;

public interface MarcaRepository {

    List<Marca> getAll();
    int create(Marca marca);

    int update(Marca marca);

    int deleteById(Long id);

    Marca findById(Long id);
}
