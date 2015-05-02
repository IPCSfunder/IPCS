package com.ipcs.controller;

import com.ipcs.controller.wrapper.AdminWrapper;
import com.ipcs.model.*;
import com.ipcs.service.AdminService;
import com.ipcs.service.RegistoryService;
import com.ipcs.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

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
    public String student() {
        return "addChildren";
    }


    @RequestMapping(value = "/persistChildren", method = RequestMethod.POST)
    public String addStudent(@RequestParam Map<String, String> requestParams, HttpSession session) throws ParseException {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        PersonDetail personDetail = AdminWrapper.personDetailWrapper(requestParams);
        Person person = new Person();
        Role role = adminservice.getRoleByName("CHILDREN");
        person.setAccount_name(personDetail.getFirstName() + personDetail.getLastName());
        person.setPassword_hash("11");
        person.setPersonDetail(personDetail);
        person.getRoles().add(role);
        person.addSchool(school);
        Contact primaryContact = AdminWrapper.primaryContactWrapper(requestParams, adminservice);
        Contact secondaryContact = AdminWrapper.secondaryContactWrapper(requestParams, adminservice);
        if (null != primaryContact)
            person.addContact(primaryContact);
        if (null != secondaryContact)
            person.addContact(secondaryContact);
        adminservice.addPerson(person);
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
        List<Person> students = adminservice.listAllPersonByRoleName(school.getName(), "CHILDREN");
        return new ModelAndView("listChildren", "command", students);
    }


    @RequestMapping(value = "/listStaff", method = RequestMethod.GET)
    public ModelAndView listStaff(HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        List<Person> students = adminservice.listAllPersonByRoleName(school.getName(), "staff");
        return new ModelAndView("listStaff", "command", students);
    }

}
