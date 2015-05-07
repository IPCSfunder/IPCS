package com.ipcs.controller.validator;

import com.ipcs.model.Activity;
import com.ipcs.model.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Chen Chao
 */
public class ActivityValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return Activity.class.equals(clazz);
    }



    @Override
    public void validate(Object obj, Errors errors) {
        // TODO Auto-generated method stub
        Activity activity = (Activity) obj;
        if (null ==activity.getName()||"".equals(activity.getName()))
            errors.rejectValue("name", null, "Activity name not provided.");
        if (null == activity.getHost().getAccount_name()||"".equals(activity.getHost().getAccount_name()))
            errors.rejectValue("host.account_name", null, "Teacher name not provided.");
        if (null == activity.getLocation()||"".equals(activity.getLocation()))
            errors.rejectValue("location", null, "Location not provided.");
        if (null == activity.getStartTime()||"".equals(activity.getStartTime()))
            errors.rejectValue("startTime", null, "StartTime not provided.");

    }
}
