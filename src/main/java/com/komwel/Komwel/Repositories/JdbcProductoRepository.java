package com.komwel.Komwel.Repositories;


import com.komwel.Komwel.Models.Marca;
import com.komwel.Komwel.Models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcProductoRepository implements ProductoRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int create(Producto producto) {
        return jdbcTemplate.update("INSERT INTO PRODUCTO (id,nombre,descripcion,precio,id_categoria,id_coleccion,id_marca,id_talla) VALUES (sec_Producto.nextval,?,?,?,?,?,?,?)",
                new Object[] {
                        producto.getNombre(), producto.getDescripcion(), producto.getPrecio(),
                        producto.getId_categoria(), producto.getId_coleccion(),producto.getId_marca(), producto.getId_talla() }
        );
    }

    @Override
    public int update(Producto producto) {
        return jdbcTemplate.update("UPDATE PRODUCTO SET nombre=?, descripcion=?, precio=?,id_categoria=?,id_coleccion=?,id_marca=?, id_talla = ? WHERE id=?",
                new Object[] {
                        producto.getNombre(), producto.getDescripcion(), producto.getPrecio(),
                        producto.getId_categoria(),producto.getId_coleccion(), producto.getId_marca(), producto.getId_talla(),
                        producto.getId() }
        );
    }

    @Override
    public int deleteById(Long id) {

        return jdbcTemplate.update("DELETE FROM PRODUCTO WHERE id=?", id);
    }

    @Override
    public List<Producto> getAll() {
        return jdbcTemplate.query("SELECT * from PRODUCTO", BeanPropertyRowMapper.newInstance(Producto.class));
    }

    @Override
    public Producto findById(Long id) {
        try {
            Producto producto = jdbcTemplate.queryForObject("SELECT * FROM PRODUCTO WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Producto.class), id);

            return producto;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
