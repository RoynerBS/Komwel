package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMarcaRepository implements MarcaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Marca> getAll() {
        return jdbcTemplate.query("SELECT * from MARCA", BeanPropertyRowMapper.newInstance(Marca.class));
    }

    @Override
    public int create(Marca marca) {
        return jdbcTemplate.update("INSERT INTO MARCA (id,nombre) VALUES(sec_Marca.nextval,?)",
                new Object[] { marca.getNombre()});
    }

    @Override
    public int update(Marca marca) {
        return jdbcTemplate.update("UPDATE MARCA SET nombre=? WHERE id=?",
                new Object[] { marca.getNombre(),marca.getId() });
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM MARCA WHERE id=?", id);
    }

    @Override
    public Marca findById(Long id) {
        try {
            Marca marca = jdbcTemplate.queryForObject("SELECT * FROM MARCA WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Marca.class), id);

            return marca;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
