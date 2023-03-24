package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Models.Talla;
import com.komwel.Komwel.Repositories.TallaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TallaController {

    @Autowired
    private TallaRepository tallaRepository;

    @GetMapping("/admin/tallas")
    public String adminTalla(Model model){
        model.addAttribute("tallalist", tallaRepository.getAll() );
        return "talla";
    }

    @GetMapping("/admin/agregar-talla")
    public String agregar_talla(Model model) {
        Talla talla =new Talla();
        model.addAttribute("talla",talla);
        return "agregar_talla";
    }

    @PostMapping("/admin/guardarTalla")
    public String guardarTalla(@ModelAttribute("talla") Talla talla) {
        tallaRepository.create(talla);
        return "redirect:/admin/tallas";
    }

    @GetMapping("/admin/borrar-talla/{id}")
    public String borrarTalla(@PathVariable("id") long id) {
        tallaRepository.deleteById(id);
        return "redirect:/admin/tallas";
    }

    @GetMapping("/admin/actualizar-talla/{id}")
    public String actualizar_talla(@PathVariable("id") long id, Model model) {
        Talla talla=tallaRepository.findById(id);
        model.addAttribute("talla",talla);
        return "actualizar_talla";
    }

    @PostMapping("/admin/actualizarTalla")
    public String actualizarTalla(@ModelAttribute("talla") Talla talla) {
        tallaRepository.update(talla);
        return "redirect:/admin/tallas";
    }
}
