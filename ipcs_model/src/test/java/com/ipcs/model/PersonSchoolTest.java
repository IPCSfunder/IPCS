package com.ipcs.model;


import java.util.Date;

import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.util.HibernateUtil;

public class PersonSchoolTest extends DBUnitTest{  
	
	
	public void testInsertPersonRole() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
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