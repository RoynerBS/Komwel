package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Talla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTallaRepository implements TallaRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Talla> getAll() {
        return jdbcTemplate.query("SELECT * from TALLA", BeanPropertyRowMapper.newInstance(Talla.class));
    }

    @Override
    public int create(Talla talla) {
        return jdbcTemplate.update("INSERT INTO TALLA (id,nombre) VALUES(sec_Talla.nextval,?)",
                new Object[] { talla.getNombre()});
    }

    @Override
    public int update(Talla talla) {
        return jdbcTemplate.update("UPDATE TALLA SET nombre=? WHERE id=?",
                new Object[] { talla.getNombre(),talla.getId() });
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM TALLA WHERE id=?", id);
    }

    @Override
    public Talla findById(Long id) {
        try {
            Talla talla = jdbcTemplate.queryForObject("SELECT * FROM TALLA WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Talla.class), id);

            return talla;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
