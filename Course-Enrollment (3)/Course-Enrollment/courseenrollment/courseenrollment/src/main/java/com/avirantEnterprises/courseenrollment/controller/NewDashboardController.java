package com.avirantEnterprises.courseenrollment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewDashboardController {

    @GetMapping("/new-dashboard")
    public String generalDashboard() {
        return "dashboard"; // dashboard.html
    }

    @GetMapping("/personalform")
    public String showPersonalForm() {
        return "personalform"; // personalform.html
    }

    // Removed @GetMapping("/form1") to avoid conflict

    @GetMapping("/e-sign")
    public String showESign() {
        return "e-sign"; // e-sign.html
    }

    @GetMapping("/govt")
    public String showGovt() {
        return "govt"; // govt.html

    }
    @GetMapping("/form1")
    public String showForm1() {
        return "form1"; // Returns form1.html
    }

    @GetMapping("/document/e-sign")
    public String showESignForm() {
        return "e-sign"; // Returns e-sign.html
    }

}

