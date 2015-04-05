/**
 * 
 */
package com.ipcs.service.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipcs.dao.PersonDao;
import com.ipcs.model.Permission;
import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.service.SecurityService;

/**
 * @author Chen Chao
 *
 */

@Service
public class SecurityServiceImpl implements SecurityService {
	@Autowired
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Transactional
	public boolean authenticateLoginInfo(String userName, String password) {
		Criterion[] criterion ={Restrictions.eq("account_name", userName)};
		List<Person> persons = personDao.createCriteria(criterion);
		if (null == persons || persons.size() > 1)
			return false;
		return persons.get(0).getPassword_hash().equals(password);
	}

	public List<Permission> listPermission(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Role> listRole(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
