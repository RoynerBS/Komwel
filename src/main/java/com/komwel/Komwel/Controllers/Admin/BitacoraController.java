package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Repositories.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BitacoraController {

    @Autowired
    private BitacoraRepository bitacoraRepository;
    @GetMapping("/admin/bitacora")
    public String adminBitacora(Model model){
        model.addAttribute("bitacoralist", bitacoraRepository.getAll() );
        return "bitacora";
    }
}
