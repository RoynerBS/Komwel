package com.komwel.Komwel.Repositories;

import com.komwel.Komwel.Models.Dashboard;
import com.komwel.Komwel.Models.Producto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDashboardRepository implements DashboardRepository{


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Dashboard find() {
            return jdbcTemplate.queryForObject("SELECT (SELECT COUNT(*) FROM USUARIO) AS CANTIDAD_USUARIOS,(SELECT COUNT(*) FROM PRODUCTO) AS CANTIDAD_PRODUCTOS,(SELECT COUNT(*) FROM CATEGORIA) AS CANTIDAD_CATEGORIAS, (SELECT COUNT(*) FROM COLECCION) AS CANTIDAD_COLECCIONES FROM DUAL",
                    BeanPropertyRowMapper.newInstance(Dashboard.class));


    }
}
