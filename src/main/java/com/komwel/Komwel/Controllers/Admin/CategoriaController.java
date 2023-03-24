package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Models.Categoria;
import com.komwel.Komwel.Models.Producto;
import com.komwel.Komwel.Repositories.CategoriaRepository;
import com.komwel.Komwel.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CategoriaController {


    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/admin/categorias")
    public String adminCategoria(Model model){
        model.addAttribute("categorialist", categoriaRepository.getAll() );
        return "categoria";
    }

    @GetMapping("/admin/agregar-categoria")
    public String agregar_categoria(Model model) {
        Categoria categoria =new Categoria();
        model.addAttribute("categoria",categoria);
        return "agregar_categoria";
    }

    @PostMapping("/admin/guardarCategoria")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaRepository.create(categoria);
        return "redirect:/admin/categorias";
    }

    @GetMapping("/admin/borrar-categoria/{id}")
    public String borrarCategoria(@PathVariable("id") long id) {
        categoriaRepository.deleteById(id);
        return "redirect:/admin/categorias";
    }

    @GetMapping("/admin/actualizar-categoria/{id}")
    public String actualizar_categoria(@PathVariable("id") long id, Model model) {
        Categoria categoria=categoriaRepository.findById(id);
        model.addAttribute("categoria",categoria);
        return "actualizar_categoria";
    }

    @PostMapping("/admin/actualizarCategoria")
    public String actualizarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaRepository.update(categoria);
        return "redirect:/admin/categorias";
    }
}
