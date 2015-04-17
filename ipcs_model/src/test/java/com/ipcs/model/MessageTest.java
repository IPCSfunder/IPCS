package com.ipcs.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.Date;

public class MessageTest extends SpringDBUnit {


    @Test
    public void testInsertMessage() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria =  session.createCriteria(MessageType.class).add(Restrictions.eq("objectId", 1l));
        MessageType messageType = (MessageType)criteria.list().get(0);
        Message message = new Message.MessageBuilder().withMessageType(messageType).withHeader("TestHeader").withContent("Hello IPCS").withAttachmentAddress("No path").withDate(new Date()).builder();

        session.save(message);
        session.getTransaction().commit();
    }


    @Test
    public void testInsertMessageWithPerson() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria =  session.createCriteria(MessageType.class).add(Restrictions.eq("objectId", 1l));
        Person person =(Person) session.get(Person.class, 2l);
        MessageType messageType = (MessageType)criteria.list().get(0);
        Message message = new Message.MessageBuilder().withMessageType(messageType).withHeader("TestHeader").withContent("Hello IPCS").withAttachmentAddress("No path").withDate(new Date()).withFromUser(person).withToUser(person).builder();
        session.save(message);
        session.getTransaction().commit();
    }



}  