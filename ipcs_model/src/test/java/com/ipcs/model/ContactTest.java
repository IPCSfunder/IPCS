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
@ContextConfiguration(locations = {"classpath:/Services.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/original/contact.xml", type = DatabaseOperation.REFRESH)
public class ContactTest {
    @Resource
    SessionFactory sessionFactory;



    @Test
    @ExpectedDatabase(value= "/expected/contact.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value= "/original/contact.xml",type = DatabaseOperation.DELETE_ALL)
    public void testInsertContact() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        RelationshipType relationshipType = (RelationshipType)session.get(RelationshipType.class, 1L);
        Person person  =(Person) session.get(Person.class,4L);
        Contact contact = new Contact.ContactBuilder().withAddress("Sengkang Street").withPostcode("12345678").withMobileNumber("123456").withPrimary(true).withContacterName("ChildFather").withRelationshipType(relationshipType).withPerson(person).builder();
        session.save(contact);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @DatabaseTearDown(value= "/original/contact.xml",type = DatabaseOperation.DELETE_ALL)
    public void testQueryContact() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("from Contact where mobileNumber = '198767654'");
        Contact contact = (Contact) cr.list().get(0);
        session.getTransaction().commit();
        Assert.assertEquals("1234223", contact.getPostcode());
        Assert.assertEquals("Child_Parent",contact.getContacterName());
        session.close();

    }


    @Test
    @DatabaseTearDown(value= "/original/contact.xml",type = DatabaseOperation.DELETE_ALL)
    public void testDelteContact() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("from Contact where mobileNumber = '198767654'");
        Contact contact = (Contact) cr.list().get(0);
        session.delete(contact);
        session.getTransaction().commit();
        session.close();

    }


}  