/**
 * 
 */
package com.ipcs.service;

import java.util.List;

import com.ipcs.model.*;
import org.springframework.stereotype.Service;

/**
 * @author Chen Chao
 *
 */

public interface AdminService {
	void addBatchSubodinates(List<Person> subodidates);
	
	void deleteBatchSubodinates(List<Person> subodinates);
	
	void addPerson(Person person);
	
	void removePerson(Person person);

	Person findPersonByName(String accountName);

	List<Person> listAllPersonByRoleName(String schoolName, String roleName);
	
	void updatePerson(Person person);
	
	Person getPersonInfo(String name);

	void deletePerson(Long personId);

	List<Person> listAllChild(Long parentId);





	RelationshipType getRelationshipTypeByName(String name);




	boolean broadcaseMessageTo(List<Person> subodidates);

	List<Message> listAllMessages(String adminName);


	List<Activity> listAllActivities(Long studentId);

	List<Activity> listAllActivitiesFromAdmin(String adminName);

	void addActivity(Activity activity);

	void updateActivity(Activity activty);

	void deleteActivity(Long activtyId);

	Activity getActivityDetail(Long activityId);

	List<Activity> listActivityByType(String typeName);

	List<ActivityType> listAllActivityType();


	Role getRoleByName(String name);



	School getSchoolByName(String name);

	List<School> getSchoolByType(String type);

}
