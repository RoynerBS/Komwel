package com.komwel.Komwel.Controllers;

import com.komwel.Komwel.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model){
        model.addAttribute("productolist", productoRepository.getAllShow() );
        return "catalogo";
    }

}
