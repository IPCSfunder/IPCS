package com.ipcs.webservice;

import com.ipcs.message.ActivityBean;
import com.ipcs.message.ActivityMsg;
import com.ipcs.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shixuan on 2015/5/5.
 */

@RestController
public class ActivityService {

    @Autowired
    private com.ipcs.service.ActivityService activityService;

    @RequestMapping("/listAllActivities")
    public ActivityMsg listAllActivities(@RequestParam(value = "id") long personID) {
        List<Activity> activities = activityService.listActivitiesByChildId(personID);
        List actBeans = new ArrayList();

        for(Activity activity: activities){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            actBeans.add(new ActivityBean(activity.getName(),activity.getLocation(),activity.getDescription(),sdf.format(activity.getStartTime()),activity.getHost().getAccount_name()));
        }

        return new ActivityMsg(personID, actBeans.size(), actBeans);
    }
}
