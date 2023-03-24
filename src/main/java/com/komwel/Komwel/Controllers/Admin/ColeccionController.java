package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Models.Coleccion;
import com.komwel.Komwel.Repositories.ColeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ColeccionController {

    @Autowired
    private ColeccionRepository coleccionRepository;

    @GetMapping("/admin/colecciones")
    public String adminColeccion(Model model){
        model.addAttribute("coleccionlist", coleccionRepository.getAll() );
        return "coleccion";
    }

    @GetMapping("/admin/agregar-coleccion")
    public String agregar_coleccion(Model model) {
        Coleccion coleccion =new Coleccion();
        model.addAttribute("coleccion",coleccion);
        return "agregar_coleccion";
    }

    @PostMapping("/admin/guardarColeccion")
    public String guardarColeccion(@ModelAttribute("coleccion") Coleccion coleccion) {
        coleccionRepository.create(coleccion);
        return "redirect:/admin/colecciones";
    }

    @GetMapping("/admin/borrar-coleccion/{id}")
    public String borrarColeccion(@PathVariable("id") long id) {
        coleccionRepository.deleteById(id);
        return "redirect:/admin/colecciones";
    }

    @GetMapping("/admin/actualizar-coleccion/{id}")
    public String actualizar_coleccion(@PathVariable("id") long id, Model model) {
        Coleccion coleccion=coleccionRepository.findById(id);
        model.addAttribute("coleccion",coleccion);
        return "actualizar_coleccion";
    }

    @PostMapping("/admin/actualizarColeccion")
    public String actualizarColeccion(@ModelAttribute("coleccion") Coleccion coleccion) {
        coleccionRepository.update(coleccion);
        return "redirect:/admin/colecciones";
    }
}
