package com.ipcs.model;


import java.util.Date;

import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.model.PersonDetail.Sex;
import com.ipcs.util.HibernateUtil;

public class PersonPersonDetailTest extends DBUnitTest{  
	
	
	public void testInsertPersonRole() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
		session.beginTransaction();
		Person person = new Person("James4","111");
		PersonDetail personDetail = new PersonDetail.PersonBuilder().withAge(23).withDob(new Date())
				.withFirstName("James").withLastName("Chen").withMarketOption(Boolean.valueOf(false))
				.withNationality("Chinese").withNickName("James").withSex(Sex.MALE).build();
		person.setPersonDetail(personDetail);
		session.save(person);
		session.getTransaction().commit();
}  
}  