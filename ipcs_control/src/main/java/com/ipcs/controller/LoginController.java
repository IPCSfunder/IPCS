package com.ipcs.controller;

import com.ipcs.service.PersonService;
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
    private PersonService personService;

    @RequestMapping(value = "*", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("login", "command", new Person());
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String addStudent(HttpSession session, @ModelAttribute Person admin, ModelMap model) {
        boolean authenticated = securityService.authenticateLoginInfo(
                admin.getAccount_name(), admin.getPassword_hash());
        if (authenticated) {
            Person authenticatedAdmin = personService.getPersonDetail ( admin.getAccount_name());
            session.setAttribute("authenticatedAdmin",authenticatedAdmin);
            return "navigator";
        } else
            return "error";
    }

    @RequestMapping(value = "/navigator", method = RequestMethod.GET)
    public String goNavigator(HttpSession session, @ModelAttribute Person admin, ModelMap model) {
        return "navigator";
    }
}
