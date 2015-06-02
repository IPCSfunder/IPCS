package com.ipcs.controller;

import com.ipcs.controller.util.BusinessConstants;
import com.ipcs.controller.util.Nationality;
import com.ipcs.controller.util.SchoolClass;
import com.ipcs.controller.validator.PersonValidator;
import com.ipcs.model.Person;
import com.ipcs.model.School;
import com.ipcs.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
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
public class ChildController {

    @Autowired
    private PersonService personService;


    @InitBinder("child")
    public void initBinderForChild(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat(BusinessConstants.DATE_FORMAT);
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.setValidator(new PersonValidator());
    }

    @RequestMapping(value = "/addChildren", method = RequestMethod.GET)
    public ModelAndView addChildren(@RequestParam Map<String, String> requestParams, HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        String account_name = requestParams.get("account_name");
        List<String> nationalities = Nationality.getNationalityList();
        List<String> classes = SchoolClass.getClassList();
        List<Person> teachers = personService.listPersonsByRoleName(school.getName(), BusinessConstants.STAFF);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nationalities", nationalities);
        modelAndView.addObject("classes", classes);
        modelAndView.addObject("teachers", teachers);
        modelAndView.setViewName("addChildren");
        if (null != account_name) {
            Person child = personService.getPersonDetail(account_name);

            modelAndView.addObject("operation", "update");
            modelAndView.addObject("child", child);
            return modelAndView;
        } else
        {
            modelAndView.addObject("operation", "add");
            modelAndView.addObject("child", new Person());
            return modelAndView;
        }
    }


    @RequestMapping(value = "/persistChild", method = RequestMethod.POST)
    public ModelAndView persistStudent(@ModelAttribute("child") @Validated Person child, BindingResult bindingResult, HttpSession session, @RequestParam Map<String, String> requestParams) throws ParseException {
        if (bindingResult.hasErrors()) {
            List<String> nationalities = Nationality.getNationalityList();
            List<String> classes = SchoolClass.getClassList();
            return new ModelAndView("addChildren", "operation", "update").addObject("nationalities", nationalities).addObject("classes", classes);
        }
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        child.setAccount_name(child.getPersonDetail().getFirstName() + BusinessConstants.NAME_CONCATENATE_SYMBOL + child.getPersonDetail().getLastName());
        child.setPassword_hash(BusinessConstants.DEFAULT_PASSWORD);
        child.setSchool(school);

        if ("update".equals(requestParams.get("operation"))) {
            personService.updatePerson(child);
            return new ModelAndView("navigator");
        }
        personService.addPerson(child);
        return new ModelAndView("navigator");
    }

    @RequestMapping(value = "/deleteChild", method = RequestMethod.GET)
    public ModelAndView deleteChild(@RequestParam Map<String, String> requestParams, HttpSession session) {
        Person person = (Person) session.getAttribute("authenticatedAdmin");
        String personId = requestParams.get("person_objid");
        personService.removePerson(Long.parseLong(personId));
        List<Person> students = personService.listPersonsByRoleName(person.getSchool().getName(), BusinessConstants.CHILDREN);
        return new ModelAndView("listChildren", "child", students);
    }


    @RequestMapping(value = "/listChildren", method = RequestMethod.GET)
    public ModelAndView listStudent(HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        List<Person> students = personService.listPersonsByRoleName(school.getName(), BusinessConstants.CHILDREN);
        return new ModelAndView("listChildren", "child", students);
    }


}
