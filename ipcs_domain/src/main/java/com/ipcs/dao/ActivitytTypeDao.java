package com.ipcs.dao;

import com.ipcs.model.Activity;
import com.ipcs.model.ActivityType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chen Chao
 */
@Repository
public class ActivitytTypeDao extends GenericHibernateDao<ActivityType, Long>{

    public ActivityType findActivityByName(String name){
        List<ActivityType> activityTypes = find("from ActivityType at where at.name='" + name + "'");
        if (null ==activityTypes || activityTypes.size() == 0){
            return null;
        }
        return activityTypes.get(0);
    }

    public List<ActivityType> listActivitiesTypes(){
        return findAll();
    }
}
