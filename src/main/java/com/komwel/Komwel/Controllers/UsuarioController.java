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
        usuarioRepository.login(usuario);
        return "redirect:/";
    }

    @GetMapping("/signin")
    public String signin(Model model){
        Usuario usuario=new Usuario();
        model.addAttribute("usuario",usuario);
        return "signin";
    }


    @PostMapping("/userRegister")
    public String userRegister(@ModelAttribute("usuario") Usuario usuario) {
        usuarioRepository.register(usuario);
        return "redirect:/login";
    }

    @GetMapping("/carrito")
    public String carrito(){
        return "ordenes";
    }

}
