package com.ipcs.controller;

import com.ipcs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ipcs.model.Person;
import com.ipcs.service.SecurityService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AdminService adminService;

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping(value = "*", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("login", "command", new Person());
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String addStudent(HttpSession session, @ModelAttribute Person admin, ModelMap model) {
        boolean authenticated = securityService.authenticateLoginInfo(
                admin.getAccount_name(), admin.getPassword_hash());
        if (authenticated) {
            Person authenticatedAdmin = adminService.getPersonInfo ( admin.getAccount_name());
            session.setAttribute("authenticatedAdmin",authenticatedAdmin);
            return "navigator";
        } else
            return "error";
    }
}
