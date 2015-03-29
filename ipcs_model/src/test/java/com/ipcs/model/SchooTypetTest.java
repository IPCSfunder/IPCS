package com.ipcs.model;


import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.util.HibernateUtil;

public class SchooTypetTest extends DBUnitTest{  
	
	public void testInsertSchoolType() {	
	Session session = HibernateUtil.getSessionFactory().openSession();

	session.beginTransaction();
	SchoolType schoolType = new SchoolType();
	schoolType.setName("Primary School");
	session.save(schoolType);
	session.getTransaction().commit();}  
}  