package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Models.Coleccion;
import com.komwel.Komwel.Models.Marca;
import com.komwel.Komwel.Repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/admin/marcas")
    public String adminMarca(Model model){
        model.addAttribute("marcalist", marcaRepository.getAll() );
        return "marca";
    }

    @GetMapping("/admin/agregar-marca")
    public String agregar_marca(Model model) {
        Marca marca =new Marca();
        model.addAttribute("marca",marca);
        return "agregar_marca";
    }

    @PostMapping("/admin/guardarMarca")
    public String guardarMarca(@ModelAttribute("marca") Marca marca) {
        marcaRepository.create(marca);
        return "redirect:/admin/marcas";
    }

    @GetMapping("/admin/borrar-marca/{id}")
    public String borrarMarca(@PathVariable("id") long id) {
        marcaRepository.deleteById(id);
        return "redirect:/admin/marcas";
    }

    @GetMapping("/admin/actualizar-marca/{id}")
    public String actualizar_marca(@PathVariable("id") long id, Model model) {
        Marca marca=marcaRepository.findById(id);
        model.addAttribute("marca",marca);
        return "actualizar_marca";
    }

    @PostMapping("/admin/actualizarMarca")
    public String actualizarMarca(@ModelAttribute("marca") Marca marca) {
        marcaRepository.update(marca);
        return "redirect:/admin/marcas";
    }
}
