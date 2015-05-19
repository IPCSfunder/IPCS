package com.ipcs.model;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
@DatabaseSetup(value="/original/relationship.xml", type= DatabaseOperation.REFRESH)
public class RelationshipTest{

	@Resource
	SessionFactory sessionFactory;

	@Test
	@ExpectedDatabase(value= "/expected/relationship.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseTearDown(value= "/original/relationship.xml",type = DatabaseOperation.DELETE_ALL)
	public void testInsertRelationship() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Relationship relationship = new Relationship();
		RelationshipType relationshipType = (RelationshipType)session.get(RelationshipType.class,1l);
		relationship.setType(relationshipType);
		Person whose = (Person)session.get(Person.class,3l);
		Person isWho = (Person)session.get(Person.class,2l);
		relationship.setWhose(whose);
		relationship.setIswho(isWho);
		session.save(relationship);
		session.getTransaction().commit();
        session.close();
	}

//	public void testQueryRelationshipWithPerson(){
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		Query cr = session.createQuery("select i from Relationship r inner join r.whose p inner join r.iswho i where p.account_name = 'Person' ");
//		Assert.assertEquals("Child", ((Person) cr.list().get(0)).getAccount_name());
//		session.getTransaction().commit();
//	}
}