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
        return Activity.class.equals(clazz);
    }



    @Override
    public void validate(Object obj, Errors errors) {
        Activity activity = (Activity) obj;
        if (null ==activity.getName()||"".equals(activity.getName()))
            errors.rejectValue("name", null, "Activity name not provided.");
        if (null == activity.getHost()||"".equals(activity.getHost().getAccount_name()))
            errors.rejectValue("host.account_name", null, "Teacher name not provided.");
        if (null == activity.getLocation()||"".equals(activity.getLocation()))
            errors.rejectValue("location", null, "Location not provided.");
        if (null == activity.getStartTime()||"".equals(activity.getStartTime()))
            errors.rejectValue("startTime", null, "Start time not provided.");
        if (null == activity.getEndTime()||"".equals(activity.getEndTime()))
            errors.rejectValue("endTime", null, "End time not provided.");
        if (null != activity.getEndTime()&&null!=activity.getStartTime())
            if((activity.getEndTime().compareTo(activity.getStartTime()))<0)
                errors.rejectValue("startTime", null, "Start time  should earlier than end time.");
    }
}
