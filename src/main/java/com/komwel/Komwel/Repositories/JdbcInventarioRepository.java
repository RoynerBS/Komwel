package com.komwel.Komwel.Repositories;


import com.komwel.Komwel.Models.Inventario;
import com.komwel.Komwel.Models.InventarioShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcInventarioRepository implements InventarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Inventario> getAll() {
        return jdbcTemplate.query("SELECT * from INVENTARIO", BeanPropertyRowMapper.newInstance(Inventario.class));
    }

    @Override
    public List<InventarioShow> getAllShow() {
        return jdbcTemplate.query("SELECT INVENTARIO.ID, PRODUCTO.NOMBRE AS PRODUCTO, INVENTARIO.CANTIDAD from INVENTARIO LEFT JOIN PRODUCTO ON PRODUCTO.ID = INVENTARIO.ID_PRODUCTO", BeanPropertyRowMapper.newInstance(InventarioShow.class));
    }

    @Override
    public int create(Inventario inventario) {
        return jdbcTemplate.update("INSERT INTO INVENTARIO (id,id_producto, cantidad) VALUES(sec_Inventario.nextval,?,?)",
                new Object[] { inventario.getId_producto(),inventario.getCantidad()});
    }

    @Override
    public int update(Inventario inventario) {
        return jdbcTemplate.update("UPDATE INVENTARIO SET cantidad=? WHERE id=?",
                new Object[] { inventario.getCantidad(),inventario.getId() });
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM INVENTARIO WHERE id=?", id);
    }

    @Override
    public Inventario findById(Long id) {
        try {
            Inventario inventario = jdbcTemplate.queryForObject("SELECT * FROM INVENTARIO WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Inventario.class), id);

            return inventario;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
