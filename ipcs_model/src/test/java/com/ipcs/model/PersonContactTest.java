package com.ipcs.model;


import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.util.HibernateUtil;

public class PersonContactTest extends DBUnitTest{  
	
	
	public void testInsertPersonRole() {
	Session session = HibernateUtil.getSessionFactory().openSession();
	 
	session.beginTransaction();
	Contact contact = new Contact("Shenzhen","186232","test@email.com");
	Person person = new Person("James4","111");
	person.setContact(contact);
//	session.save(role);
	session.save(person);
	session.getTransaction().commit();
}  
}  