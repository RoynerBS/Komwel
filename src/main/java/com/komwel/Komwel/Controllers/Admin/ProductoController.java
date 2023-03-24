package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Models.Categoria;
import com.komwel.Komwel.Models.Producto;
import com.komwel.Komwel.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ColeccionRepository coleccionRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private TallaRepository tallaRepository;


    @GetMapping("/admin/productos")
    public String adminProducto(Model model){
        model.addAttribute("productolist", productoRepository.getAll() );
        return "producto";
    }

    @GetMapping("/admin/agregar-producto")
    public String agregar_producto(Model model, Model model1, Model model2, Model model3, Model model4) {
        Producto producto=new Producto();

        model.addAttribute("producto",producto);

        model1.addAttribute("categorialist", categoriaRepository.getAll());
        model2.addAttribute("coleccionlist", coleccionRepository.getAll());
        model3.addAttribute("marcalist", marcaRepository.getAll());
        model4.addAttribute("tallalist", tallaRepository.getAll());
        return "agregar_producto";
    }

    @PostMapping("/admin/guardarProducto")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoRepository.create(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/admin/borrar-producto/{id}")
    public String borrarProducto(@PathVariable("id") long id) {
        productoRepository.deleteById(id);
        return "redirect:/admin/productos";
    }

    @GetMapping("/admin/actualizar-producto/{id}")
    public String actualizar_producto(@PathVariable("id") long id, Model model, Model model1, Model model2, Model model3, Model model4) {
        Producto producto=productoRepository.findById(id);
        model.addAttribute("producto",producto);


        model1.addAttribute("categorialist", categoriaRepository.getAll());
        model2.addAttribute("coleccionlist", coleccionRepository.getAll());
        model3.addAttribute("marcalist", marcaRepository.getAll());
        model4.addAttribute("tallalist", tallaRepository.getAll());


        return "actualizar_producto";
    }

    @PostMapping("/admin/actualizarProducto")
    public String actualizarProducto(@ModelAttribute("producto") Producto producto) {
        productoRepository.update(producto);
        return "redirect:/admin/productos";
    }


}
