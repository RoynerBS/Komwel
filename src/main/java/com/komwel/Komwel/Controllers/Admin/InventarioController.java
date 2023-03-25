package com.komwel.Komwel.Controllers.Admin;


import com.komwel.Komwel.Models.Inventario;
import com.komwel.Komwel.Models.Marca;
import com.komwel.Komwel.Repositories.InventarioRepository;
import com.komwel.Komwel.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/admin/inventario")
    public String admininventario(Model model){
        model.addAttribute("inventariolist", inventarioRepository.getAllShow() );
        return "inventario";
    }

    @GetMapping("/admin/agregar-inventario")
    public String agregar_inventario(Model model, Model model1) {
        Inventario inventario=new Inventario();
        model.addAttribute("inventario",inventario);
        model1.addAttribute("productolist", productoRepository.getAll() );
        return "agregar_inventario";
    }

    @PostMapping("/admin/guardarInventario")
    public String guardarInventario(@ModelAttribute("inventario") Inventario inventario) {
        inventarioRepository.create(inventario);
        return "redirect:/admin/inventario";
    }

    @GetMapping("/admin/borrar-inventario/{id}")
    public String borrarInventario(@PathVariable("id") long id) {
        inventarioRepository.deleteById(id);
        return "redirect:/admin/inventario";
    }

    @GetMapping("/admin/actualizar-inventario/{id}")
    public String actualizar_inventario(@PathVariable("id") long id, Model model) {
        Inventario inventario=inventarioRepository.findById(id);
        model.addAttribute("inventario",inventario);
        return "actualizar_inventario";
    }

    @PostMapping("/admin/actualizarInventario")
    public String actualizarInventario(@ModelAttribute("inventario") Inventario inventario) {
        inventarioRepository.update(inventario);
        return "redirect:/admin/inventario";
    }
}
