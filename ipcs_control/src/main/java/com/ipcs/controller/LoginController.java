package com.ipcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ipcs.model.Person;
import com.ipcs.service.SecurityService;

@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;    

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping(value = "/userlogin", method = RequestMethod.GET)
    public ModelAndView student() {
	return new ModelAndView("login", "command", new Person());
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute Person admin, ModelMap model) {
	boolean authenticated = securityService.authenticateLoginInfo(
		admin.getAccount_name(), admin.getPassword_hash());
	if (authenticated) {
	    model.addAttribute("name", admin.getAccount_name());
	    model.addAttribute("password", admin.getPassword_hash());
	    return "result";
	} else
	    return "error";
    }
}
