package com.ipcs.model;


import java.util.Date;

import org.hibernate.Session;

import com.ipcs.model.PersonDetail.Sex;
import org.junit.Test;

public class PersonDetailTest extends SpringDBUnit{
	
	@Test
	public void testInsertPersonRole() {
	Session session = sessionFactory.openSession();
	 
	session.beginTransaction();
	PersonDetail personDetail = new PersonDetail.PersonBuilder().withAge(23).withDob(new Date())
			.withFirstName("James").withLastName("Chen").withMarketOption(Boolean.valueOf(false))
			.withNationality("Chinese").withNickName("James").withSex(Sex.MALE).build();
	Person person = new Person("James4","111");
	personDetail.setPerson(person);
	session.save(personDetail);
	session.getTransaction().commit();
}  
}  