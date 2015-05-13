package com.ipcs.model;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class MessageTest extends SpringDBUnit {

    @Test
    public void testOrder(){
        testInsertMessage();
        testInsertMessageWithPerson();
        testUpdateMessageUser();
        testQueryMessageUser();
        testDeleteMessage();
    }



    public void testInsertMessage() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria =  session.createCriteria(MessageType.class).add(Restrictions.eq("objectId", 1l));
        MessageType messageType = (MessageType)criteria.list().get(0);
        Message message = new Message.MessageBuilder().withMessageType(messageType).withHeader("TestHeader").withContent("Hello IPCS").withAttachmentAddress("No path").withDate(new Date()).builder();

        session.save(message);
        session.getTransaction().commit();
    }


    public void testInsertMessageWithPerson() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria =  session.createCriteria(MessageType.class).add(Restrictions.eq("objectId", 1l));
        Person fromUser =(Person) session.get(Person.class, 2l);
        Person toUser =(Person) session.get(Person.class, 3l);
        MessageType messageType = (MessageType)criteria.list().get(0);
        Message message = new Message.MessageBuilder().withMessageType(messageType).withHeader("TestHeader").withContent("Hello IPCS").withAttachmentAddress("No path").withDate(new Date()).withFromUser(fromUser).builder();
        session.save(message);
        session.getTransaction().commit();
    }

    public void testUpdateMessageUser(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query =  session.createQuery("from Message m where m.header = 'TestHeader'");
        Person toUser =(Person) session.get(Person.class, 4l);
        Message message = (Message)query.list().get(0);
        session.update(message);
        session.getTransaction().commit();
    }

    public void testQueryMessageUser(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query =  session.createQuery("from Message m left join  fetch  m.fromUser p where p.account_name = 'Child'");
        Assert.assertEquals("TestHeader",((Message) query.list().get(0)).getHeader());
        session.getTransaction().commit();

    }

    public void testDeleteMessage(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query =  session.createQuery("from Message m where m.header = 'TestHeader'");
        Message message = (Message)query.list().get(0);
        session.delete(message);
        session.getTransaction().commit();

    }




}