package com.ipcs.model;


import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcsutil.HibernateUtil;

public class PersonDaoTest extends DBUnitTest{  
	
	ThreadLocal test = new ThreadLocal();
	
	public void testInsertPersonRole() {
	Session session = HibernateUtil.getSessionFactory().openSession();
	 
	session.beginTransaction();
	Role role = new Role("Merchant4");
	Person person = new Person("James4","111");
	person.addRole(role);
	
//	session.save(role);
	session.save(person);
	session.getTransaction().commit();
}  
}  