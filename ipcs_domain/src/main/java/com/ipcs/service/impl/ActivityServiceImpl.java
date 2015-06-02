/**
 *
 */
package com.ipcs.service.impl;

import com.ipcs.dao.ActivityDao;
import com.ipcs.dao.ActivitytTypeDao;
import com.ipcs.dao.PersonDao;
import com.ipcs.dao.SchoolDao;
import com.ipcs.model.Activity;
import com.ipcs.model.ActivityType;
import com.ipcs.model.Person;
import com.ipcs.model.School;
import com.ipcs.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Chao
 */

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private ActivityDao activityDao;


    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private ActivitytTypeDao activityTypeDao;


    @Transactional(readOnly = true)
    public List<Activity> listActivitiesByChildId(Long childId) {
        return activityDao.listActivitieByChild(childId);
    }


    @Transactional(readOnly = true)
    public List<Activity> listActivitiesByAdminName(String adminName) {
        return activityDao.listActivitiesByAdmin(adminName);
    }

    @Transactional
    public void addActivity(Activity activity) {
        List<Person> trainsientPersons = new ArrayList<Person>();
        List<Person> students = activity.getPersons();
        if (null != students && students.size() != 0) {
            trainsientPersons.addAll(students);
            activity.getPersons().clear();
            ;
            for (Person person : trainsientPersons) {
                person = personDao.findPersonByName(person.getAccount_name());
                if (null != person)
                    activity.addPerson(person);
            }
        }
        ActivityType activityType = activity.getActivityType();
        if (null != activityType) {
            activity.setActivityType(activityTypeDao.findActivityByName(activityType.getName()));
        }
        Person host = personDao.findPersonByName(activity.getHost().getAccount_name());
        activity.setHost(host);
        School school = schoolDao.findSchoolByName(activity.getSchool().getName());
        activity.setSchool(school);
        activityDao.save(activity);
    }

    @Transactional
    public void updateActivity(Activity activity) {
        {
            Activity persistedActivity = activityDao.load(activity.getObjectId());
            persistedActivity.setName(activity.getName());
            persistedActivity.setDescription(activity.getDescription());
            persistedActivity.setLocation(activity.getLocation());
            persistedActivity.setStartTime(activity.getStartTime());

            Person persistedHost = personDao.findPersonByName(activity.getHost().getAccount_name());
            persistedActivity.setHost(persistedHost);

            List<Person> students = activity.getPersons();
            if (null != students && students.size() > 0) {
                persistedActivity.getPersons().clear();
                for (Person person : students) {
                    person = personDao.findPersonByName(person.getAccount_name());
                    if (null != person)
                        persistedActivity.addPerson(person);
                }
            }

            ActivityType activityType = activityTypeDao.findActivityByName(activity.getActivityType().getName());
            persistedActivity.setActivityType(activityType);
            activityDao.update(persistedActivity);
        }
    }


    @Transactional
    public void deleteActivity(Long activtyId) {
        Activity activity = activityDao.get(activtyId);
        activityDao.delete(activity);
    }

    @Transactional(readOnly = true)
    public Activity getActivityDetail(Long activityId) {
        return activityDao.findActivityDetailsById(activityId);
    }

    @Transactional(readOnly = true)
    public List<Activity> listActivityByType(String typeName) {
        return activityDao.listActivitiesByType(typeName);
    }

    @Transactional(readOnly = true)
    public List<ActivityType> listActivityTypes(){
        return activityTypeDao.listActivitiesTypes();
    }
}
