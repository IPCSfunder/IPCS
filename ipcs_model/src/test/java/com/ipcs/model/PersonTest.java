package com.ipcs.model;


import java.util.List;
import java.util.Set;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/Services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
@DatabaseSetup(value="/original/person.xml", type= DatabaseOperation.REFRESH)
public class PersonTest{

	@Resource
	SessionFactory sessionFactory;

	@Test
	@ExpectedDatabase(value= "/expected/person.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseTearDown(value= "/original/person.xml",type = DatabaseOperation.DELETE_ALL)
	public void testInsertPersonRole() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = new Person("LuisWang", "12345");
		School school = (School)session.get(School.class, 1l);
		person.setSchool(school);
		Role role = (Role)session.get(Role.class,1l);
		role.addPerson(person);
		session.save(person);
		session.save(role);
		session.getTransaction().commit();
		session.close();
	}





	public void testCriteria(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Person.class).add(Restrictions.eq("objectId", 2l));
		List<Person> list = cr.list();
		session.getTransaction().commit();
		Assert.assertEquals("Child", list.get(0).getAccount_name());
	}

	public void testCriteriaQuery(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query cr = session.createQuery("from Person where objectId = 2 ");
		List<Person> list = cr.list();
		session.getTransaction().commit();
		Assert.assertEquals("Child", list.get(0).getAccount_name());
	}


	public void testGetRelationship() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = (Person)session.get(Person.class,2l);
//		List<Relationship> relationshipSet = person.getRelationships();
		Relationship teacherRelationship =null;
//		for(Relationship relationship:relationshipSet){
//			if(relationship.getType().getName().equals("TEACHER"))
//				teacherRelationship = relationship;
//		}

		Assert.assertEquals(teacherRelationship.getIswho().getAccount_name(), "Teacher");
		session.getTransaction().commit();

	}

	public void testGetMessage(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = (Person)session.get(Person.class, 1l);
//		Assert.assertEquals(relationshipSet.iterator().next().getIswho().getAccount_name(),"Teacher");
		session.getTransaction().commit();

	}
}