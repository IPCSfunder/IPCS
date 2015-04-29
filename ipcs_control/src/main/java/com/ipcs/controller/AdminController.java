package com.ipcs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ipcs.model.PersonDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String student() {
        return "addChildren";
    }




    @RequestMapping(value = "/persistChildren", method = RequestMethod.POST)
    public String addStudent(@RequestParam Map<String,String> requestParams,HttpSession session) throws ParseException {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchools().iterator().next();
        String firstName = requestParams.get("fist_name");
        String lastName = requestParams.get("last_name");
        Date dateOfBirth = (new SimpleDateFormat("yyyy-mm-dd")).parse(requestParams.get("date_of_birth"));
        PersonDetail.Sex sex = requestParams.get("sex")=="FEMALE"?PersonDetail.Sex.FEMALE:PersonDetail.Sex.MALE;
        Integer age = Integer.valueOf(requestParams.get("age"));
        String nationality = requestParams.get("nationality");
        PersonDetail personDetail = new PersonDetail.PersonBuilder().withFirstName(firstName).withLastName(lastName).withAge(age).withDob(dateOfBirth)
                .withSex(sex).withNationality(nationality).build();
        Person person  = new Person();
        Role role = adminservice.getRoleByName("CHILDREN");
        person.setAccount_name(firstName + lastName);
        person.setPassword_hash("11");
        person.setPersonDetail(personDetail);
        person.getRoles().add(role);
        person.addSchool(school);
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
