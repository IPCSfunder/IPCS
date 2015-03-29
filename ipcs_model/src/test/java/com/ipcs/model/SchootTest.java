package com.ipcs.model;


import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.util.HibernateUtil;

public class SchootTest extends DBUnitTest{  
	
	public void testInsertSchool() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		School school = new School();
		school.setAddress("Shen Zhen");
		school.setName("SZ primary school");
		
		SchoolType schoolType = new SchoolType();
		schoolType.setName("Primary School");
		
		school.setType(schoolType);
		session.save(school);
		session.getTransaction().commit();

		
		

}  
}  