/**
 * 
 */
package com.ipcs.service.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipcs.dao.PersonDao;
import com.ipcs.model.Person;
import com.ipcs.service.RegistoryService;

/**
 * @author Chen Chao
 *
 */

@Service
public class RegistoryServiceImpl implements RegistoryService{
    @Autowired
    private PersonDao personDao;

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void registerNewPerson(Person person) {
	personDao.save(person);
    }


    public String retrievePasswordByName(String userName) {
	Criterion[] criterion = { Restrictions.eq("account_name", userName) };
	List<Person> persons = personDao.createCriteria(criterion);
	if (null == persons || persons.size() > 1)
	    return null;
	return persons.get(0).getPassword_hash();
    }


    public String retrievePasswordByContactNumber(String phoneNumber) {
	return null;
    }

}
