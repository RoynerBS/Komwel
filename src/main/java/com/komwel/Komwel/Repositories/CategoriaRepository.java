package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Categoria;



import java.util.List;

public interface CategoriaRepository {

    List<Categoria> getAll();
    int create(Categoria categoria);

    int update(Categoria categoria);

    int deleteById(Long id);

    Categoria findById(Long id);

}
