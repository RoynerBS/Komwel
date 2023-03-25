package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Bitacora;
import com.komwel.Komwel.Models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcBitacoraRepository implements BitacoraRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Bitacora> getAll() {
        return jdbcTemplate.query("SELECT * from BITACORA", BeanPropertyRowMapper.newInstance(Bitacora.class));
    }
}
