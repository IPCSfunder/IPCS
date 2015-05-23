package com.ipcs.model;


import java.text.SimpleDateFormat;
import java.util.List;
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
public class PersonTest{

	@Resource
	SessionFactory sessionFactory;

	@Test
	@DatabaseSetup(value="/original/person.xml", type= DatabaseOperation.REFRESH)
	@ExpectedDatabase(value= "/expected/person.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseTearDown(value= "/original/person.xml",type = DatabaseOperation.DELETE_ALL)
	public void testInsertPersonRole() throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = new Person("LuisWang", "12345");
		School school = (School)session.get(School.class, 1l);
		person.setSchool(school);
		Role role = (Role)session.get(Role.class, 1l);
		person.addRole(role);
		Contact contact = (Contact)session.load(Contact.class, 1L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PersonDetail personDetail = new PersonDetail.PersonBuilder().withAge(25).withDob(sdf.parse("1986-08-25"))
				.withFirstName("Richard").withLastName("Gard").withMarketOption(Boolean.valueOf(false))
				.withNationality("Singaporean").withNickName("GG").withNric("1234567").withSex("FEMALE").build();
		person.setPersonDetail(personDetail);
		session.save(person);
		contact.setPerson(person);
		session.getTransaction().commit();
		session.close();
	}


	@Test
	@DatabaseSetup(value="/original/person.xml", type= DatabaseOperation.REFRESH)
	@DatabaseTearDown(value= "/original/person.xml",type = DatabaseOperation.DELETE_ALL)
	public void testDeletePerson(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = (Person) session.load(Person.class,1L);
		session.delete(person);
		Criteria cr = session.createCriteria(Person.class).add(Restrictions.eq("objectId", 1l));
		Assert.assertEquals(0,cr.list().size());
		session.getTransaction().commit();
		session.close();
	}




	@Test
	@DatabaseSetup(value="/original/person.xml", type= DatabaseOperation.REFRESH)
	@DatabaseTearDown(value= "/original/person.xml",type = DatabaseOperation.DELETE_ALL)
	public void testUpdate(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = (Person) session.load(Person.class,1L);
		person.getRoles().clear();
		person.setSchool(null);
		person.getActivities().clear();
		Assert.assertEquals(0,person.getRoles().size());
		session.getTransaction().commit();
		session.close();
	}

	public void testCriteriaQuery(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query cr = session.createQuery("from Person where objectId = 2 ");
		List<Person> list = cr.list();
		session.getTransaction().commit();
		Assert.assertEquals("Child", list.get(0).getAccount_name());
		session.close();
	}


	public void testGetRelationship() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person person = (Person) session.get(Person.class,2l);
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
		Person person = (Person) session.get(Person.class, 1l);
//		Assert.assertEquals(relationshipSet.iterator().next().getIswho().getAccount_name(),"Teacher");
		session.getTransaction().commit();

	}
}