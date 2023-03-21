package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Models.Producto;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

    @GetMapping("/producto")
    public String adminProducto(){
        return "producto";
    }

    @GetMapping("/agregar_producto")
    public String agregar_producto(Model model) {
        Producto producto=new Producto();
        model.addAttribute("producto",producto);
        return "agregar_producto";
    }

    @PostMapping("/agregar_producto")
    public String agregar_producto(@ModelAttribute("producto") Producto producto) {
        //repo.save(student);
        return "redirect:/producto";
    }

}
