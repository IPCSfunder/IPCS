package com.ipcs.model;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/Services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
@DatabaseSetup(value="/original/message.xml", type= DatabaseOperation.REFRESH)
public class MessageTest{
    @Resource
    SessionFactory sessionFactory;


    @Test
    @ExpectedDatabase(value= "/expected/message.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value= "/original/message.xml",type = DatabaseOperation.DELETE_ALL)
    public void testInsertMessage() throws ParseException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        MessageType messageType = (MessageType)session.get(MessageType.class,1L);
        Person fromUser = (Person)session.get(Person.class, 1L);
        Message message = new Message.MessageBuilder().withMessageType(messageType).withHeader("TestHeader").withContent("Hello IPCS").withAttachmentAddress("No path").withMessageType(messageType).withAttachmentAddress("/root/messages").withFromUser(fromUser).builder();
        session.save(message);
        session.getTransaction().commit();
        session.close();
    }


    @Test
    @ExpectedDatabase(value= "/expected/messageUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value= "/original/message.xml",type = DatabaseOperation.DELETE_ALL)
    public void testUpdateMessage(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query =  session.createQuery("from Message m where m.header = 'hello'");
        Person toUser =(Person) session.get(Person.class, 4l);
        Message message = (Message)query.list().get(0);
        message.setHeader("hello man");
        message.setContent("hello badman!");
        session.update(message);
        session.getTransaction().commit();
        session.close();
    }

//    public void testQueryMessageUser(){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Query query =  session.createQuery("from Message m left join  fetch  m.fromUser p where p.account_name = 'Child'");
//        Assert.assertEquals("TestHeader",((Message) query.list().get(0)).getHeader());
//        session.getTransaction().commit();
//
//    }

    @Test
    @DatabaseTearDown(value= "/original/message.xml",type = DatabaseOperation.DELETE_ALL)
    public void testDeleteMessage(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query =  session.createQuery("from Message m where m.header = 'hello'");
        Message message = (Message)query.list().get(0);
        session.delete(message);
        session.getTransaction().commit();
        session.close();

    }




}