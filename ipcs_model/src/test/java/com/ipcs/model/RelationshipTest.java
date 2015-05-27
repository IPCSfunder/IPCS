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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/Services.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/original/relationship.xml", type = DatabaseOperation.REFRESH)
public class RelationshipTest {

    @Resource
    SessionFactory sessionFactory;

    @Test
    @ExpectedDatabase(value = "/expected/relationship.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "/original/relationship.xml", type = DatabaseOperation.DELETE_ALL)
    public void testInsertRelationship() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Relationship relationship = new Relationship();
        RelationshipType relationshipType = (RelationshipType) session.get(RelationshipType.class, 1l);
        relationship.setType(relationshipType);
        Person whose = (Person) session.get(Person.class, 3l);
        Person isWho = (Person) session.get(Person.class, 2l);
        relationship.setWhose(whose);
        relationship.setIswho(isWho);
        session.save(relationship);
        session.getTransaction().commit();
        session.close();


        session = sessionFactory.openSession();
        session.beginTransaction();
        relationship = (Relationship) session.get(Relationship.class, relationship.getObjectId());
        Assert.assertEquals(relationship.getIswho().getAccount_name(), "WilliamWang");
        Assert.assertEquals(relationship.getWhose().getAccount_name(), "JohnasonChen");
        session.getTransaction().commit();
        session.close();
    }


    @Test
    @DatabaseTearDown(value = "/original/relationship.xml", type = DatabaseOperation.DELETE_ALL)
    public void testDeleteRelationshipWithPerson() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Relationship relationship = (Relationship) session.get(Relationship.class, 1L);
        session.delete(relationship);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        Person iswho = (Person) session.get(Person.class, 1L);
        Person whose = (Person) session.get(Person.class, 2L);
        relationship = (Relationship) session.get(Relationship.class, 1L);
        session.getTransaction().commit();
        session.close();
        Assert.assertEquals(iswho.getAccount_name(), "JamesChen");
        Assert.assertEquals(whose.getAccount_name(), "WilliamWang");
        Assert.assertNull(relationship);
    }
}