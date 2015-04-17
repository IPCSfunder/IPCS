package com.ipcs.model;


import org.hibernate.Session;
import org.junit.Test;

public class SchooTypetTest extends SpringDBUnit{

	@Test
	public void testInsertSchoolType() {	
	Session session = sessionFactory.openSession();

	session.beginTransaction();
	SchoolType schoolType = new SchoolType();
	schoolType.setName("Primary School");
	session.save(schoolType);
	session.getTransaction().commit();}  
}  