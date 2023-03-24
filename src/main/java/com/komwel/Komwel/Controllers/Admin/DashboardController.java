package com.komwel.Komwel.Controllers.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(){
        return "dashboard";
    }


}
