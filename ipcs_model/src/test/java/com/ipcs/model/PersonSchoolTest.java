package com.ipcs.model;


import org.hibernate.Session;;
import org.junit.Test;

public class PersonSchoolTest extends SpringDBUnit{
	
	@Test
	public void testInsertPersonRole() {
		Session session = sessionFactory.openSession();
		 
		session.beginTransaction();
		Person person = new Person("James41","111");
		School school = new School();
		school.setAddress("Shen Zhen");
		school.setName("SZ primary school");
		
		SchoolType schoolType = new SchoolType();
		schoolType.setName("Primary School");
		
		school.setType(schoolType);	
		
		session.save(schoolType);
		session.save(school);
		
		person.addSchool(school);
		session.save(person);
		session.getTransaction().commit();
}  
}  