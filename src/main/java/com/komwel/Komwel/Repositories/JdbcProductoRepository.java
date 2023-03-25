package com.komwel.Komwel.Repositories;


import com.komwel.Komwel.Models.Marca;
import com.komwel.Komwel.Models.Producto;
import com.komwel.Komwel.Models.ProductoShow;
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
    public int verificar(Producto producto) {
        int resultado = jdbcTemplate.queryForObject("SELECT crud.verificar_producto (?) FROM DUAL",new Object[] {
                producto.getNombre()}, Integer.class );
        return resultado;
    }

    @Override
    public int verificarDep(Producto producto) {
        int resultado = jdbcTemplate.queryForObject("SELECT crud.verificar_dep_producto_inventario (?) FROM DUAL",new Object[] {
                producto.getNombre()}, Integer.class );
        return resultado;
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
        return jdbcTemplate.update("call crud.borrar_producto(?)",id);
    }

    @Override
    public List<Producto> getAll() {
        return jdbcTemplate.query("SELECT * from PRODUCTO", BeanPropertyRowMapper.newInstance(Producto.class));
    }

    @Override
    public List<ProductoShow> getAllShow() {
        return jdbcTemplate.query("SELECT PRODUCTO.ID, PRODUCTO.NOMBRE, PRODUCTO.DESCRIPCION, PRODUCTO.PRECIO, CATEGORIA.NOMBRE AS CATEGORIA, \n" +
                "COLECCION.NOMBRE AS COLECCION, MARCA.NOMBRE AS MARCA, TALLA.NOMBRE AS TALLA from PRODUCTO LEFT JOIN CATEGORIA ON PRODUCTO.ID_CATEGORIA = CATEGORIA.ID\n" +
                "LEFT JOIN COLECCION ON PRODUCTO.ID_COLECCION = COLECCION.ID\n" +
                "LEFT JOIN MARCA ON PRODUCTO.ID_MARCA = MARCA.ID\n" +
                "LEFT JOIN TALLA ON PRODUCTO.ID_TALLA = TALLA.ID", BeanPropertyRowMapper.newInstance(ProductoShow.class));
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

    @Override
    public Producto findByName(String name) {
        try {
            Producto producto = jdbcTemplate.queryForObject("SELECT * FROM PRODUCTO WHERE nombre=?",
                    BeanPropertyRowMapper.newInstance(Producto.class), name);
            return producto;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
