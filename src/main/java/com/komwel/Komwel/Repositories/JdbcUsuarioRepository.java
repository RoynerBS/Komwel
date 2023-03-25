package com.komwel.Komwel.Repositories;


import com.komwel.Komwel.Models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcUsuarioRepository implements UsuarioRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    private SimpleJdbcCall simpleJdbcCall;



    @Override
    public int register(Usuario usuario) {

        return jdbcTemplate.update("call crud.agregar_usuario(?,?,?,?)",
                new Object[] {
                       usuario.getNombre(), usuario.getApellido(), usuario.getEmail(),
                      usuario.getContrasena()}
        );
    }


    @Override
    public int login(Usuario usuario) {
        int resultado = jdbcTemplate.queryForObject("SELECT consulta.iniciar_sesion (?,?) FROM DUAL",new Object[] {
                usuario.getEmail(),
                usuario.getContrasena()}, Integer.class );
        return resultado;
    }

    @Override
    public int toAdmin(Usuario usuario) {
        return jdbcTemplate.update("UPDATE USUARIO SET isAdmin=? WHERE id=?",
                new Object[] { usuario.isAdmin(), usuario.getId() });
    }

    @Override
    public int verif(Usuario usuario) {
        int resultado = jdbcTemplate.queryForObject("SELECT consulta.verificar_correo (?) FROM DUAL",new Object[] {
                usuario.getEmail()}, Integer.class );
        return resultado;
    }


}
