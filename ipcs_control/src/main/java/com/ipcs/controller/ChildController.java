package com.ipcs.controller;

import com.ipcs.controller.util.BusinessConstants;
import com.ipcs.controller.util.Nationality;
import com.ipcs.controller.util.SchoolClass;
import com.ipcs.controller.validator.PersonValidator;
import com.ipcs.model.Activity;
import com.ipcs.model.Person;
import com.ipcs.model.RelationshipType;
import com.ipcs.model.School;
import com.ipcs.service.ActivityService;
import com.ipcs.service.PersonService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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

    @Autowired
    private ActivityService activityService;


    @InitBinder("child")
    public void initBinderForChild(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "activities", new CustomCollectionEditor(List.class)
        {
            @Override
            protected Object convertElement(Object element)
            {
                Long id = null;
                if(element instanceof String && !((String)element).equals("")){
                    //From the JSP 'element' will be a String
                    try{
                        id = Long.parseLong((String) element);
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                else if(element instanceof Long) {
                    //From the database 'element' will be a Long
                    id = (Long) element;
                }
                Activity activity = new Activity();
                activity.setObjectId(id);
                return id != null ? activity : null;
            }
        });


        SimpleDateFormat sdf = new SimpleDateFormat(BusinessConstants.DATE_FORMAT);
        sdf.setLenient(false);
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.setValidator(new PersonValidator());
    }

    @RequestMapping(value = "/addChildren", method = RequestMethod.GET)
    public ModelAndView addChildren(@RequestParam Map<String, String> requestParams, HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        String account_name = requestParams.get("account_name");
        List<String> nationalities = Nationality.getNationalityList();
        List<Activity> classes = activityService.listActivityByType("CLASS");
        List<Person> teachers = personService.listPersonsByRoleName(school.getName(), BusinessConstants.STAFF);
        List<RelationshipType> relationshipTypes = personService.listRelationshipTypes();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nationalities", nationalities);
        modelAndView.addObject("classes", classes);
        modelAndView.addObject("teachers", teachers);
        modelAndView.addObject("relationshipTypes", relationshipTypes);
        modelAndView.setViewName("addChildren");
        if (null != account_name) {
            Person child = personService.getPersonDetail(account_name);

            modelAndView.addObject("operation", "update");
            modelAndView.addObject("child", child);
            return modelAndView;
        } else {
            modelAndView.addObject("operation", "add");
            modelAndView.addObject("child", new Person());
            return modelAndView;
        }
    }


    @RequestMapping(value = "/viewChildren", method = RequestMethod.GET)
    public ModelAndView viewChildren(@RequestParam Map<String, String> requestParams, HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        String account_name = requestParams.get("account_name");
        List<String> nationalities = Nationality.getNationalityList();
        List<Activity> classes = activityService.listActivityByType("CLASS");
        List<Person> teachers = personService.listPersonsByRoleName(school.getName(), BusinessConstants.STAFF);
        List<RelationshipType> relationshipTypes = personService.listRelationshipTypes();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nationalities", nationalities);
        modelAndView.addObject("classes", classes);
        modelAndView.addObject("teachers", teachers);
        modelAndView.addObject("relationshipTypes", relationshipTypes);
        modelAndView.setViewName("addChildren");
        Person child = personService.getPersonDetail(account_name);

        modelAndView.addObject("operation", BusinessConstants.VIEW);
        modelAndView.addObject("child", child);
        return modelAndView;
    }

    @RequestMapping(value = "/persistChild", method = RequestMethod.POST)
    public ModelAndView persistStudent(@ModelAttribute("child") @Validated Person child, BindingResult bindingResult, HttpSession session, @RequestParam Map<String, String> requestParams) throws ParseException {
        if (bindingResult.hasErrors()) {
            School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
            List<String> nationalities = Nationality.getNationalityList();
            List<Activity> classes = activityService.listActivityByType("CLASS");
            List<Person> teachers = personService.listPersonsByRoleName(school.getName(), BusinessConstants.STAFF);
            List<RelationshipType> relationshipTypes = personService.listRelationshipTypes();
            if ("update".equals(requestParams.get("operation"))) {
                return new ModelAndView("addChildren", "operation", BusinessConstants.UPDATE).addObject("nationalities", nationalities).addObject("classes", classes).addObject("teachers", teachers).addObject("relationshipTypes", relationshipTypes);
            }else
                return new ModelAndView("addChildren", "operation", BusinessConstants.ADD).addObject("nationalities", nationalities).addObject("classes", classes).addObject("teachers", teachers).addObject("relationshipTypes", relationshipTypes);
        }
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        child.setAccount_name(child.getPersonDetail().getFirstName() + BusinessConstants.NAME_CONCATENATE_SYMBOL + child.getPersonDetail().getLastName());
        child.setPassword_hash(BusinessConstants.DEFAULT_PASSWORD);
        child.setSchool(school);
        //Get age
        Date dob = child.getPersonDetail().getDateOfBirth();
        DateTime jodaDob = new DateTime(dob);
        int age = Years.yearsBetween(jodaDob.toLocalDate(), new LocalDate()).getYears();
        child.getPersonDetail().setAge(age);
        if (BusinessConstants.UPDATE.equals(requestParams.get("operation"))) {
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
