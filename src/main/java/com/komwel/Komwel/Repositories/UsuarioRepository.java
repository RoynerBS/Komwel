package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Usuario;

public interface UsuarioRepository {

    int register(Usuario usuario);
    int login(Usuario usuario);
    int toAdmin(Usuario usuario);
    int verif(Usuario usuario);
}
