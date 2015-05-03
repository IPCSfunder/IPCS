package com.ipcs.controller;

import com.ipcs.controller.validator.PersonValidator;
import com.ipcs.model.*;
import com.ipcs.service.AdminService;
import com.ipcs.service.RegistoryService;
import com.ipcs.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.setValidator(new PersonValidator());
    }

    @RequestMapping(value = "/addChildren", method = RequestMethod.GET)
    public ModelAndView student(@RequestParam Map<String,String> requestParams) {
        String account_name = requestParams.get("account_name");
        if(null!=account_name){
            Person child = adminservice.getChildDetail(account_name);
            return new ModelAndView("addChildren", "command", child);
        }
        else
            return new ModelAndView("addChildren", "command", new Person());
    }


    @RequestMapping(value = "/persistChild", method = RequestMethod.POST)
    public String persistStudent(@ModelAttribute("command") @Validated Person child, BindingResult bindingResult, HttpSession session,ModelMap model) throws ParseException {
        if(bindingResult.hasErrors())
            return "addChildren";
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        child.setAccount_name(child.getPersonDetail().getFirstName()+child.getPersonDetail().getLastName());
        child.setPassword_hash("11");
        child.addSchool(school);
        adminservice.addPerson(child);
        return "navigator";
    }





    @RequestMapping(value = "/addStaff", method = RequestMethod.GET)
    public ModelAndView staff(@RequestParam Map<String,String> requestParams) {
        String account_name = requestParams.get("account_name");
        if(null!=account_name){
            Person staff = adminservice.getChildDetail(account_name);
            return new ModelAndView("addStaff", "command", staff);
        }
        else
            return new ModelAndView("addStaff", "command", new Person());
    }


    @RequestMapping(value = "/persistStaff", method = RequestMethod.POST)
    public String persistStaff(@ModelAttribute("command") @Validated Person staff, BindingResult bindingResult, HttpSession session,ModelMap model) throws ParseException {
        if(bindingResult.hasErrors())
            return "addStaff";
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        staff.setAccount_name(staff.getPersonDetail().getFirstName()+staff.getPersonDetail().getLastName());
        staff.setPassword_hash("11");
        staff.addSchool(school);
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
        List<Person> staffs = adminservice.listAllPersonByRoleName(school.getName(), "STAFF");
        return new ModelAndView("listStaff", "command", staffs);
    }

}
