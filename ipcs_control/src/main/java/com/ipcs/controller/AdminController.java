package com.ipcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.model.School;
import com.ipcs.service.AdminService;
import com.ipcs.service.RegistoryService;
import com.ipcs.service.SecurityService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AdminService adminservice;

    @Autowired
    private RegistoryService registoryService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setAdminservice(AdminService adminservice) {
        this.adminservice = adminservice;
    }

    public void setRegistoryService(RegistoryService registoryService) {
        this.registoryService = registoryService;
    }

    @RequestMapping(value = "/addChildren", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("addChildren", "command", new Person());
    }

    @RequestMapping(value = "/persistChildren", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute Person children, ModelMap model) {
        adminservice.addPerson(children);
        return "navigator";
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
    public ModelAndView teacher() {
        return new ModelAndView("addTeacher", "command", new Person());
    }

    @RequestMapping(value = "/persistStaff", method = RequestMethod.POST)
    public String addTeacher(@ModelAttribute Person staff, ModelMap model) {
        adminservice.addPerson(staff);
        return "navigator";
    }

    @RequestMapping(value = "/listChildren", method = RequestMethod.GET)
    public ModelAndView listStudent(HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        List<Person> students = adminservice.listAllPersonByRoleName(school.getName(), "children");
        return new ModelAndView("listChildren", "command", students);
    }


    @RequestMapping(value = "/listStaff", method = RequestMethod.GET)
    public ModelAndView listStaff(HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        List<Person> students = adminservice.listAllPersonByRoleName(school.getName(), "staff");
        return new ModelAndView("listStaff", "command", students);
    }

}
