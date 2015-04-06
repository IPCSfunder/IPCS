package com.ipcs.model;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;

import com.ipcs.util.HibernateUtil;

public class PersonTest extends DBUnitTest{  
	
	
	public void testInsertPersonRole() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		Role role = new Role("Merchant4");
		Person person = new Person("James4", "111");
		person.addRole(role);

		session.save(role);
		session.save(person);
		session.getTransaction().commit();
		
	}  
	
	
	public void testCriteria(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Person.class).add(Restrictions.eq("objectId", 2l));
		List<Person> list = cr.list(); 
		session.getTransaction().commit();
		Assert.assertEquals("Person", list.get(0).getAccount_name());
	}
	
	public void testCriteriaQuery(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query cr = session.createQuery("from Person where objectId = 2 ");
		List<Person> list = cr.list(); 
		session.getTransaction().commit();
		Assert.assertEquals("Person", list.get(0).getAccount_name());
	}
}  