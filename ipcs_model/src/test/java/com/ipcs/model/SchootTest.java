package com.ipcs.model;


import org.hibernate.Session;
import org.junit.Test;

public class SchootTest extends SpringDBUnit{

	@Test
	public void testInsertSchool() {

		Session session = sessionFactory.openSession();

		session.beginTransaction();
		School school = new School();
		school.setAddress("Shen Zhen");
		school.setName("SZ primary school");
		
		SchoolType schoolType = new SchoolType();
		schoolType.setName("Primary School");
		
		school.setType(schoolType);
		session.save(schoolType);
		session.save(school);
		session.getTransaction().commit();
}  
}  