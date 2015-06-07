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

public interface PersonService {
	void addBatchSubodinates(List<Person> subodidates);

	void deleteBatchSubodinates(List<Person> subodinates);
	
	void addPerson(Person person);
	
	void removePerson(Long personId);

	Person findPersonByName(String name);

	List<Person> listPersonsByRoleName(String schoolName, String roleName);
	
	void updatePerson(Person person);

	Person getPersonDetail(String name);
	
	List<Person> listChildsByParentId(Long parentId);

	List<RelationshipType> listRelationshipTypes();
}
