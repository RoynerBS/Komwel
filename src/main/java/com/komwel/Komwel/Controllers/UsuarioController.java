package com.komwel.Komwel.Controllers;
import com.komwel.Komwel.Models.Usuario;
import com.komwel.Komwel.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login(Model model){
        Usuario usuario=new Usuario();
        model.addAttribute("usuario",usuario);
        return "login";
    }

    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute("usuario") Usuario usuario) {
        int resultado = usuarioRepository.login(usuario);
        if(resultado == 1){
            return "redirect:/admin/dashboard";
        }
        else{
            return "redirect:/error";
        }

    }

    @GetMapping("/signin")
    public String signin(Model model){
        Usuario usuario=new Usuario();
        model.addAttribute("usuario",usuario);
        return "signin";
    }


    @PostMapping("/userRegister")
    public String userRegister(@ModelAttribute("usuario") Usuario usuario) {
        int resultado = usuarioRepository.verif(usuario);
        if(resultado == 0){
            usuarioRepository.register(usuario);
            return "redirect:/login";
        }
        return "redirect:/error";

    }

    @GetMapping("/carrito")
    public String carrito(){
        return "ordenes";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

}
