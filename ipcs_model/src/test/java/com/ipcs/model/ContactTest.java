package com.ipcs.model;


import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.util.HibernateUtil;

public class ContactTest extends DBUnitTest{  
	
	public void testInsertPersonRole() {
	Session session = HibernateUtil.getSessionFactory().openSession();
	 
	session.beginTransaction();
	session.beginTransaction();
	Contact contact = new Contact("Shenzhen","186232","test@email.com");
//	session.save(role);
	session.save(contact);
	session.getTransaction().commit();
}  
}  