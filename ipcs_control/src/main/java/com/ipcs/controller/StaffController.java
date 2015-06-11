package com.ipcs.controller;

import com.ipcs.controller.util.BusinessConstants;
import com.ipcs.controller.util.Nationality;
import com.ipcs.controller.validator.PersonValidator;
import com.ipcs.controller.validator.StaffValidator;
import com.ipcs.model.Person;
import com.ipcs.model.School;
import com.ipcs.service.PersonService;
import com.ipcs.service.RegistoryService;
import com.ipcs.service.SecurityService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;
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
public class StaffController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PersonService personService;

    @Autowired
    private RegistoryService registoryService;

    @InitBinder("command")
    public void initBinderForStaff(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat(BusinessConstants.DATE_FORMAT);
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.setValidator(new StaffValidator());
    }

    @RequestMapping(value = "/addStaff", method = RequestMethod.GET)
    public ModelAndView addStaff(@RequestParam Map<String,String> requestParams) {
        String account_name = requestParams.get("account_name");
        List<String> nationalities = Nationality.getNationalityList();
        if(null!=account_name&&!account_name.equals("")){
            Person staff = personService.getPersonDetail(account_name);
            return new ModelAndView("addStaff", "command", staff).addObject("operation", BusinessConstants.UPDATE).addObject("nationalities", nationalities);
        }
        else
            return new ModelAndView("addStaff", "command", new Person()).addObject("operation", BusinessConstants.ADD).addObject("nationalities", nationalities);
    }


    @RequestMapping(value = "/viewStaff", method = RequestMethod.GET)
    public ModelAndView viewStaff(@RequestParam Map<String,String> requestParams) {
        String account_name = requestParams.get("account_name");
        List<String> nationalities = Nationality.getNationalityList();
        Person staff = personService.getPersonDetail(account_name);
        return new ModelAndView("addStaff", "command", staff).addObject("operation", BusinessConstants.VIEW).addObject("nationalities", nationalities);
    }


    @RequestMapping(value = "/persistStaff", method = RequestMethod.POST)
    public ModelAndView addStaff(@ModelAttribute("command") @Validated Person staff, BindingResult bindingResult, HttpSession session,ModelMap model,@RequestParam Map<String,String> requestParams) throws ParseException {
        if(bindingResult.hasErrors()) {
            List<String> nationalities = Nationality.getNationalityList();
            if(BusinessConstants.UPDATE.equals(requestParams.get("operation"))) {
                return new ModelAndView("addStaff", "operation", BusinessConstants.UPDATE).addObject("nationalities", nationalities);
            }
            else
                return new ModelAndView("addStaff", "operation", BusinessConstants.ADD).addObject("nationalities", nationalities);
        }
        //Get age
        Date dob = staff.getPersonDetail().getDateOfBirth();
        DateTime jodaDob = new DateTime(dob);
        int age = Years.yearsBetween(jodaDob.toLocalDate(), new LocalDate()).getYears();
        staff.getPersonDetail().setAge(age);
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        staff.setAccount_name(staff.getPersonDetail().getFirstName() + BusinessConstants.NAME_CONCATENATE_SYMBOL + staff.getPersonDetail().getLastName());
        if(BusinessConstants.UPDATE.equals(requestParams.get("operation"))) {
            personService.updatePerson(staff);
            return new ModelAndView("navigator");
        }

        staff.setPassword_hash(BusinessConstants.DEFAULT_PASSWORD);
        staff.setSchool(school);
        personService.addPerson(staff);
        return new ModelAndView("navigator");
    }


    @RequestMapping(value = "/deleteStaff", method = RequestMethod.GET)
    public ModelAndView deleteChild(@RequestParam Map<String, String> requestParams, HttpSession session) {
        Person person = (Person) session.getAttribute("authenticatedAdmin");
        String personId = requestParams.get("person_objid");
        personService.removePerson(Long.parseLong(personId));
        List<Person> staffs = personService.listPersonsByRoleName(person.getSchool().getName(), BusinessConstants.STAFF);
        return new ModelAndView("listStaff", "staffs", staffs);
    }


    @RequestMapping(value = "/listStaff", method = RequestMethod.GET)
    public ModelAndView listStaff(HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        List<Person> staffs = personService.listPersonsByRoleName(school.getName(), BusinessConstants.STAFF);
        return new ModelAndView("listStaff", "command", staffs);
   }
}
