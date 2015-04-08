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

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public ModelAndView student() {
	return new ModelAndView("addStudent", "command", new Person());
    }

    @RequestMapping(value = "/persistStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute Person student, ModelMap model) {
	        Role studentRole = adminservice.getRoleByName("student");
	        School school = adminservice.getSchoolByName("PUNGOL");
	        student.addRole(studentRole);
	        student.addSchool(school);
	        registoryService.registerNewPerson(student);
	        return "navigator";
    }
    
    @RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
    public ModelAndView teacher() {
	return new ModelAndView("addTeacher", "command", new Person());
    }
    
    @RequestMapping(value = "/persistTeacher", method = RequestMethod.POST)
    public String addTeacher(@ModelAttribute Person teacher, ModelMap model) {
	        Role teacherRole = adminservice.getRoleByName("teacher");
	        School school = adminservice.getSchoolByName("PUNGOL");
	        teacher.addRole(teacherRole);
	        teacher.addSchool(school);
	        registoryService.registerNewPerson(teacher);
	        return "navigator";
    }
    
    @RequestMapping(value = "/listStudent", method = RequestMethod.GET)
    public ModelAndView listStudent() {
	        List<Person> students = adminservice.listAllPersonByRoleName("PUNGOL", "student");
	        return new ModelAndView("listStudent", "command", students);
    }
    
}
