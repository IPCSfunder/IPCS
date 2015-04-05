/**
 * 
 */
package com.ipcs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipcs.dao.PersonDao;
import com.ipcs.model.Person;
import com.ipcs.model.School;
import com.ipcs.service.AdminService;

/**
 * @author Chen Chao
 *
 */

@Service
public class AdminServiceImpl implements AdminService<Person>{
	@Autowired
	private PersonDao personDao;
	
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Transactional
	public void addBatchSubodinates(List<Person> subodidates) {
	    personDao.batchSave(subodidates);
	}
	
	@Transactional
	public void addAdmin(Person admin){
	    personDao.save(admin);
	}

	@Transactional
	public List<Person> listAllStudents(String schoolName) {
	    return (List<Person>)personDao.find("select p from Person as p left join p.schools s left join p.roles as r where  s.name = '"+schoolName+"' and r.name = 'student'");
	}


	public List<Person> listAllTeachers(String schoolName) {
	    return (List<Person>)personDao.find("select p from Person as p left join p.schools s left join p.roles as r where  s.name = '"+schoolName+"' and r.name = 'teacher'");
	}

	/* (non-Javadoc)
	 * @see com.ipcs.service.AdminService#updateChild(java.lang.Object)
	 */
	public boolean updateChild(Person person) {
	    // TODO Auto-generated method stub
	    return false;
	}

	/* (non-Javadoc)
	 * @see com.ipcs.service.AdminService#updateTeacher(java.lang.Object)
	 */
	public boolean updateTeacher(Person teacher) {
	    // TODO Auto-generated method stub
	    return false;
	}

	/* (non-Javadoc)
	 * @see com.ipcs.service.AdminService#broadcaseMessageTo(java.util.List)
	 */
	public boolean broadcaseMessageTo(List<Person> subodidates) {
	    // TODO Auto-generated method stub
	    return false;
	}


	@Transactional
	public void deleteBatchSubodinates(List<Person> subodinates) {
	    personDao.deleteAll(subodinates);
	}
}
