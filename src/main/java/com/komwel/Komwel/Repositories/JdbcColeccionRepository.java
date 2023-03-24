package com.komwel.Komwel.Repositories;


import com.komwel.Komwel.Models.Coleccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcColeccionRepository implements ColeccionRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Coleccion> getAll() {
        return jdbcTemplate.query("SELECT * from COLECCION", BeanPropertyRowMapper.newInstance(Coleccion.class));
    }

    @Override
    public int create(Coleccion coleccion) {
        return jdbcTemplate.update("INSERT INTO COLECCION (id,nombre) VALUES(sec_Coleccion.nextval,?)",
                new Object[] { coleccion.getNombre()});
    }

    @Override
    public int update(Coleccion coleccion) {
        return jdbcTemplate.update("UPDATE COLECCION SET nombre=? WHERE id=?",
                new Object[] { coleccion.getNombre(),coleccion.getId() });
    }

    @Override
    public int deleteById(Long id) {

        return jdbcTemplate.update("DELETE FROM COLECCION WHERE id=?", id);
    }

    @Override
    public Coleccion findById(Long id) {
        try {
            Coleccion coleccion = jdbcTemplate.queryForObject("SELECT * FROM COLECCION WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Coleccion.class), id);

            return coleccion;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
