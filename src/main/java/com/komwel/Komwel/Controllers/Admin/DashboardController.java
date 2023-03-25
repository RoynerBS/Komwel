package com.komwel.Komwel.Controllers.Admin;

import com.komwel.Komwel.Repositories.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DashboardRepository dashboardRepository;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model){
        model.addAttribute("dashboard", dashboardRepository.find() );
        return "dashboard";
    }


}
