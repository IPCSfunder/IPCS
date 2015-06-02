/**
 * 
 */
package com.ipcs.service;

import com.ipcs.model.*;

import java.util.List;

/**
 * @author Chen Chao
 *
 */

public interface ActivityService {
	List<Activity> listActivitiesByChildId(Long childId);

	List<Activity> listActivitiesByAdminName(String adminName);

	void addActivity(Activity activity);

	void updateActivity(Activity activty);

	void deleteActivity(Long activtyId);

	Activity getActivityDetail(Long activityId);

	List<Activity> listActivityByType(String typeName);

	List<ActivityType> listActivityTypes();
}
