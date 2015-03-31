package com.ipcs.integration;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;

import com.ipcs.dao.GenericHibernateDao;
import com.ipcs.dao.PersonDao;
import com.ipcs.model.Person;

public abstract class BaseTest {
	PersonDao personDao = null;
	

	@BeforeClass
	public void setUp() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"GenericHiberDao.xml");
		personDao = (PersonDao) appContext
				.getBean("personDao");
	}
	
	public Session getNewSession(){
		return  personDao.getSessionFactory().openSession();
	}
	
	public Session getCurrentSession(){
		return  personDao.getSessionFactory().getCurrentSession();
	}

}
