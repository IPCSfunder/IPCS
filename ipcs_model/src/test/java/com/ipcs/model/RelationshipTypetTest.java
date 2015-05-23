package com.ipcs.model;


import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
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
@ContextConfiguration(locations = {"classpath:/Services.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class RelationshipTypetTest{
    @Resource
    SessionFactory sessionFactory;

    @Test
    @DatabaseSetup(value = "/original/relationshipType.xml", type = DatabaseOperation.REFRESH)
    @ExpectedDatabase(value= "/expected/relationshipType.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value= "/original/relationshipType.xml", type = DatabaseOperation.DELETE_ALL)
    public void testInsertRelationshipType() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        RelationshipType relationshipType = new RelationshipType();
        relationshipType.setName("MOTHER");
        session.save(relationshipType);
        session.getTransaction().commit();
        session.close();
    }


    @Test
    @DatabaseSetup(value = "/original/relationshipType.xml", type = DatabaseOperation.REFRESH)
    @DatabaseTearDown(value= "/original/relationshipType.xml", type = DatabaseOperation.DELETE_ALL)
    public void testQueryRelationshipType() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        RelationshipType relationshipType = (RelationshipType)session.get(RelationshipType.class, 1L);
        session.getTransaction().commit();
        Assert.assertEquals("FATHER", relationshipType.getName());
        session.close();
    }
}