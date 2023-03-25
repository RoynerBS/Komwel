package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Categoria;
import com.komwel.Komwel.Models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCategoriaRepository implements CategoriaRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Categoria> getAll() {
        return jdbcTemplate.query("SELECT * from CATEGORIA", BeanPropertyRowMapper.newInstance(Categoria.class));
    }

    @Override
    public int create(Categoria categoria) {
        return jdbcTemplate.update("INSERT INTO CATEGORIA (id,nombre) VALUES(sec_Categoria.nextval,?)",
                new Object[] { categoria.getNombre()});
    }

    @Override
    public int update(Categoria categoria) {

        return jdbcTemplate.update("UPDATE CATEGORIA SET nombre=? WHERE id=?",
                new Object[] { categoria.getNombre(),categoria.getId() });
    }

    @Override
    public int deleteById(Long id) {

        return jdbcTemplate.update("DELETE FROM CATEGORIA WHERE id=?", id);
    }

    @Override
    public Categoria findById(Long id) {
        try {
            Categoria categoria = jdbcTemplate.queryForObject("SELECT * FROM CATEGORIA WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Categoria.class), id);

            return categoria;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
