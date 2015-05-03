package com.ipcs.model;


import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;

public class PersonPersonDetailTest extends SpringDBUnit{
	@Test
	public void testOrder(){
		testInsertPersonDetails();
		testDelete();
	}
	
	public void testInsertPersonDetails() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = new Person("James5","111");
		PersonDetail personDetail = new PersonDetail.PersonBuilder().withAge(23).withDob(new Date())
				.withFirstName("James").withLastName("Chen").withMarketOption(Boolean.valueOf(false))
				.withNationality("Chinese").withNickName("James").withSex("MALE").build();
		person.setPersonDetail(personDetail);
		session.save(person);
		session.getTransaction().commit();
}

	public void testDelete(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = (Person)session.createQuery("from Person p where p.account_name = 'James5'").list().get(0);

		session.delete(person);
		session.getTransaction().commit();

	}

}  