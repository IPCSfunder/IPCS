package com.ipcs.dao;

import com.ipcs.model.Activity;
import com.ipcs.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chen Chao
 */
@Repository
public class ActivityDao extends GenericHibernateDao<Activity, Long>{

    public Activity findActivityDetailsById(Long activityId){
        List<Activity> activities = find("select ac from Activity ac inner join fetch ac.host inner join fetch ac.school where ac.objectId = '" + activityId + "'");
        if(activities.size() == 0)
            return null;
        return activities.get(0);
    }


    public List<Activity> listActivitieByChild(Long childId) {
        return find("select m from Activity m inner join m.persons p where p.objectId = '" + childId + "'");
    }

    public List<Activity> listActivitiesByAdmin(String adminName){
        return find("select ac from Person p inner join p.school s inner join s.activities ac where p.account_name='" + adminName + "'");
    }

    public List<Activity> listActivitiesByType(String typeName){
        return find("select ac from Activity ac inner join fetch ac.activityType at where at.name='"+typeName+"'");
    }
}
