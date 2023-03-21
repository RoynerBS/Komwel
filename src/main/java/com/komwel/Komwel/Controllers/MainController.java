package com.komwel.Komwel.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/catalogo")
    public String catalogo(){
        return "catalogo";
    }

    @GetMapping("/catalogo/{id}")
    public String catalogoCategoria(){
        return "catalogo";
    }

    @GetMapping("/producto/{id}")
    public String productoDetalle(){ return "producto_detalle"; }

}
