package com.ipcs.model;


import org.hibernate.Session;
import org.junit.Test;

public class PersonContactTest extends SpringDBUnit{
	

	@Test
	public void testInsertPersonRole() {
	Session session = sessionFactory.openSession();
	 
	session.beginTransaction();
	Contact contact = new Contact("Shenzhen","186232","test@email.com");
	Person person = new Person("James4","111");
	person.setContact(contact);
	session.save(person);
	session.getTransaction().commit();
}  
}  