package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Talla;

import java.util.List;

public interface TallaRepository {

    List<Talla> getAll();
    int create(Talla talla);

    int update(Talla talla);

    int deleteById(Long id);

    Talla findById(Long id);
}
