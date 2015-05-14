package com.ipcs.controller;

import com.ipcs.controller.validator.ActivityValidator;
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
import java.util.*;

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


    @InitBinder("command")
    public void initBinderForChild(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.setValidator(new PersonValidator());
    }

    @InitBinder("activity")
    public void initBinderForActivity(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.setValidator(new ActivityValidator());
    }


    @RequestMapping(value = "/addChildren", method = RequestMethod.GET)
    public ModelAndView student(@RequestParam Map<String,String> requestParams) {
        String account_name = requestParams.get("account_name");
        if(null!=account_name){
            Person child = adminservice.getChildDetail(account_name);
            return new ModelAndView("addChildren", "command", child).addObject("operation","update");
        }
        else
            return new ModelAndView("addChildren", "command", new Person()).addObject("operation","add");
    }


    @RequestMapping(value = "/persistChild", method = RequestMethod.POST)
    public String persistStudent(@ModelAttribute("command") @Validated Person child, BindingResult bindingResult, HttpSession session,@RequestParam Map<String,String> requestParams) throws ParseException {
        if(bindingResult.hasErrors())
            return "addChildren";
        if("update".equals(requestParams.get("operation"))) {
            adminservice.updatePerson(child);
            return "navigator";
        }
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
            return new ModelAndView("addStaff", "command", staff).addObject("operation","update");
        }
        else
            return new ModelAndView("addStaff", "command", new Person()).addObject("operation","add");
    }


    @RequestMapping(value = "/persistStaff", method = RequestMethod.POST)
    public String persistStaff(@ModelAttribute("command") @Validated Person staff, BindingResult bindingResult, HttpSession session,ModelMap model,@RequestParam Map<String,String> requestParams) throws ParseException {
        if(bindingResult.hasErrors())
            return "addStaff";
        if("update".equals(requestParams.get("operation"))) {
            adminservice.updatePerson(staff);
            return "navigator";
        }
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

    @RequestMapping(value = "/listActivity", method = RequestMethod.GET)
    public ModelAndView listActivity(HttpSession session) {
        Person person = (Person) session.getAttribute("authenticatedAdmin");
        List<Activity> activities =adminservice.listAllActivitiesFromAdmin(person.getAccount_name());
        return new ModelAndView("listActivities", "command", activities);
    }

    @RequestMapping(value = "/addActivity", method = RequestMethod.GET)
    public ModelAndView addActivity(@RequestParam Map<String,String> requestParams, HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        List<Person> teachers = adminservice.listAllPersonByRoleName(school.getName(), "STAFF");
        List<Person> students = adminservice.listAllPersonByRoleName(school.getName(), "CHILDREN");
        String activityId = requestParams.get("activityId");
        if(null!=activityId){
            Activity activity = adminservice.getActivityDetail(Long.parseLong(activityId));
            return new ModelAndView("addActivity", "activity", activity).addObject("operation","update").addObject("teachers", teachers).addObject("students",students);
        }
        else
            return new ModelAndView("addActivity", "activity", new Activity()).addObject("operation","add").addObject("teachers", teachers).addObject("students",students);
    }

    @RequestMapping(value = "/persistActivity", method = RequestMethod.POST)
    public ModelAndView persistActivity(@ModelAttribute("activity")  @Validated Activity activity, BindingResult bindingResult, HttpSession session,ModelMap model,@RequestParam Map<String,String> requestParams) throws ParseException {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        List<Person> teachers = adminservice.listAllPersonByRoleName(school.getName(), "STAFF");
        List<Person> students = adminservice.listAllPersonByRoleName(school.getName(), "CHILDREN");

        if(bindingResult.hasErrors())
            return  new ModelAndView("addActivity", "operation", "add").addObject("teachers", teachers).addObject("students",students);
        activity.setSchool(school);

        if("update".equals(requestParams.get("operation"))) {
            adminservice.updateActivity(activity);
            return new ModelAndView("navigator").addObject("teachers", teachers).addObject("students",students);
        }
        adminservice.addActivity(activity);
        return new ModelAndView("navigator").addObject("teachers", teachers).addObject("students",students);
    }


    @RequestMapping(value = "/deleteActivity", method = RequestMethod.GET)
    public ModelAndView deleteActivity(@RequestParam Map<String,String> requestParams, HttpSession session) {
        Person person = (Person) session.getAttribute("authenticatedAdmin");
        String activityId = requestParams.get("activityId");
        adminservice.deleteActivity(Long.parseLong(activityId));
        List<Activity> activities =adminservice.listAllActivitiesFromAdmin(person.getAccount_name());
        return new ModelAndView("listActivities", "command", activities);
    }


}
