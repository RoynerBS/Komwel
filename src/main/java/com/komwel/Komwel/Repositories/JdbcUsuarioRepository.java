package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Producto;
import com.komwel.Komwel.Models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUsuarioRepository implements UsuarioRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int register(Usuario usuario) {
        return jdbcTemplate.update("INSERT INTO usuario (nombre, apellido, email, contrasena) VALUES(?,?,?,?)",
                new Object[] { usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getContrasena() });
    }

    @Override
    public int login(Usuario usuario) {
        return jdbcTemplate.update("INSERT INTO usuario (nombre, apellido, email, contrasena) VALUES(?,?,?,?)",
                new Object[] { usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getContrasena() });
    }

    @Override
    public int toAdmin(Usuario usuario) {
        return jdbcTemplate.update("UPDATE USUARIO SET isAdmin=? WHERE id=?",
                new Object[] { usuario.isAdmin(), usuario.getId() });
    }


}
