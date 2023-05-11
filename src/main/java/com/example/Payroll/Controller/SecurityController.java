package com.example.Payroll.Controller;

import com.example.Payroll.Repository.EmpRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController
{
    @GetMapping("/")
    public String welcomePage() {

        return "welcome";
    }

    @Autowired
    EmpRepo empRepo;

    @Autowired
    HttpSession session;
    @GetMapping("/default/")
    public String showDefaultPageAfterLogin(HttpServletRequest request, @CurrentSecurityContext(expression = "authentication?.name") String email)
    {
        session.setAttribute("idUser", empRepo.getByEmail(email).getId());

        if (request.isUserInRole("ROLE_ADMIN"))
        {
            return "redirect:/admin/firstPage/";
        }
        else if (request.isUserInRole("ROLE_EMP"))
        {
            return "redirect:/emp/firstPage/";
        }
        // similarly you can check for other designations like manager, analyst etc

        return "redirect:/";
    }

    @GetMapping("/login")
    public String LoginPage()
    {
        return "login";
    }
}


