/**
 * 
 */
package com.ipcs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.model.School;

/**
 * @author Chen Chao
 *
 */

public interface AdminService {
	public void addBatchSubodinates(List<Person> subodidates);
	
	public void deleteBatchSubodinates(List<Person> subodinates);
	
	public void addPerson(Person person);	
	
	public void removePerson(Person person);

	public List<Person> listAllPersonByRoleName(String schoolName, String roleName);
	
	public void updatePerson(Person person);
	
	public boolean broadcaseMessageTo(List<Person> subodidates);
	
	public Role getRoleByName(String name);
	
	public School getSchoolByName(String name);
	
	public List<School> getSchoolByType(String type);
	

}
