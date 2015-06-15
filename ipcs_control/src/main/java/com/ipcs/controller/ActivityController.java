package com.ipcs.controller;

import com.ipcs.controller.util.BusinessConstants;
import com.ipcs.controller.validator.ActivityValidator;
import com.ipcs.model.Activity;
import com.ipcs.model.ActivityType;
import com.ipcs.model.Person;
import com.ipcs.model.School;
import com.ipcs.service.ActivityService;
import com.ipcs.service.PersonService;
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
public class ActivityController {
    @Autowired
    private PersonService personService;

    @Autowired
    private ActivityService activityService;

    @InitBinder("activity")
    public void initBinderForActivity(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat(BusinessConstants.DATETIME_FORMAT);
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.setValidator(new ActivityValidator());
    }


    @RequestMapping(value = "/listActivity", method = RequestMethod.GET)
    public ModelAndView listActivity(HttpSession session) {
        Person person = (Person) session.getAttribute("authenticatedAdmin");
        List<Activity> activities = activityService.listActivitiesByAdminName(person.getAccount_name());
        return new ModelAndView("listActivities", "activities", activities);
    }

    @RequestMapping(value = "/addActivity", method = RequestMethod.GET)
    public ModelAndView addActivity(@RequestParam Map<String, String> requestParams, HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        List<Person> teachers = personService.listPersonsByRoleName(school.getName(), BusinessConstants.STAFF);
        List<Person> students = personService.listPersonsByRoleName(school.getName(), BusinessConstants.CHILDREN);
        List<ActivityType> activityTypes = activityService.listActivityTypes();
        String activityId = requestParams.get("activityId");

        ModelAndView modelAndView = new ModelAndView("addActivity");
        modelAndView.addObject("teachers", teachers);
        modelAndView.addObject("students", students);
        modelAndView.addObject("activityTypes", activityTypes);

        if (null != activityId&&!activityId.equals("")) {
            Activity activity = activityService.getActivityDetail(Long.parseLong(activityId));
            modelAndView.addObject("activity", activity);
            modelAndView.addObject("operation", BusinessConstants.UPDATE);
            return modelAndView;
        } else {
            modelAndView.addObject("activity", new Activity());
            modelAndView.addObject("operation", BusinessConstants.ADD);
            return modelAndView;
        }
    }


    @RequestMapping(value = "/viewActivity", method = RequestMethod.GET)
    public ModelAndView viewActivity(@RequestParam Map<String, String> requestParams, HttpSession session) {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        List<Person> teachers = personService.listPersonsByRoleName(school.getName(), BusinessConstants.STAFF);
        List<Person> students = personService.listPersonsByRoleName(school.getName(), BusinessConstants.CHILDREN);
        List<ActivityType> activityTypes = activityService.listActivityTypes();
        String activityId = requestParams.get("activityId");

        ModelAndView modelAndView = new ModelAndView("addActivity");
        modelAndView.addObject("teachers", teachers);
        modelAndView.addObject("students", students);
        modelAndView.addObject("activityTypes", activityTypes);
        Activity activity = activityService.getActivityDetail(Long.parseLong(activityId));
        modelAndView.addObject("activity", activity);
        modelAndView.addObject("operation", BusinessConstants.VIEW);
        return modelAndView;
    }

    @RequestMapping(value = "/persistActivity", method = RequestMethod.POST)
    public ModelAndView persistActivity(@ModelAttribute("activity") @Validated Activity activity, BindingResult bindingResult, HttpSession session, ModelMap model, @RequestParam Map<String, String> requestParams) throws ParseException {
        School school = ((Person) session.getAttribute("authenticatedAdmin")).getSchool();
        List<Person> teachers = personService.listPersonsByRoleName(school.getName(), BusinessConstants.STAFF);
        List<Person> students = personService.listPersonsByRoleName(school.getName(), BusinessConstants.CHILDREN);
        List<ActivityType> activityTypes = activityService.listActivityTypes();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teachers", teachers);
        modelAndView.addObject("students", students);
        modelAndView.addObject("activityTypes", activityTypes);
        modelAndView.setViewName("addActivity");

        activity.setSchool(school);
        if (BusinessConstants.UPDATE.equals(requestParams.get("operation"))) {
            if (bindingResult.hasErrors()) {
                modelAndView.addObject("operation", BusinessConstants.UPDATE);
                return modelAndView;
            }
            activityService.updateActivity(activity);
            return new ModelAndView("navigator").addObject("teachers", teachers).addObject("students", students);
        } else {
            if (bindingResult.hasErrors()) {
                modelAndView.addObject("operation", BusinessConstants.ADD);
                return modelAndView;
            }
            activityService.addActivity(activity);
            return new ModelAndView("navigator").addObject("teachers", teachers).addObject("students", students);

        }
}


    @RequestMapping(value = "/deleteActivity", method = RequestMethod.GET)
    public ModelAndView deleteActivity(@RequestParam Map<String, String> requestParams, HttpSession session) {
        Person person = (Person) session.getAttribute("authenticatedAdmin");
        String activityId = requestParams.get("activityId");
        activityService.deleteActivity(Long.parseLong(activityId));
        List<Activity> activities = activityService.listActivitiesByAdminName(person.getAccount_name());
        return new ModelAndView("listActivities", "activities", activities);
    }


}
