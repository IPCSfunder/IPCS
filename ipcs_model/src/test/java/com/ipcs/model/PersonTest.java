package com.ipcs.model;


import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest extends SpringDBUnit{

	@Test
	public void testInsertPersonRole() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		Role role = new Role("Merchant4");
		Person person = new Person("James4", "111");
		Role role = (Role)session.get(Role.class,1l);
		person.addRole(role);
		session.save(person);
		session.getTransaction().commit();

	}




	@Test
	public void testCriteria(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Person.class).add(Restrictions.eq("objectId", 2l));
		List<Person> list = cr.list();
		session.getTransaction().commit();
		Assert.assertEquals("Person", list.get(0).getAccount_name());
	}

	@Test
	public void testCriteriaQuery(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query cr = session.createQuery("from Person where objectId = 2 ");
		List<Person> list = cr.list();
		session.getTransaction().commit();
		Assert.assertEquals("Person", list.get(0).getAccount_name());
	}


	@Test
	public void testGetRelationship() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = (Person)session.get(Person.class,3l);
		Set<Relationship> relationshipSet = person.getRelationships();
		Assert.assertEquals(relationshipSet.iterator().next().getIswho().getAccount_name(),"Person");
		session.getTransaction().commit();

	}
}  