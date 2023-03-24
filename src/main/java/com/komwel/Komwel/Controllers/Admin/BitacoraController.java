package com.komwel.Komwel.Controllers.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BitacoraController {

    @GetMapping("/admin/bitacora")
    public String adminBitacora(){
        return "bitacora";
    }
}
