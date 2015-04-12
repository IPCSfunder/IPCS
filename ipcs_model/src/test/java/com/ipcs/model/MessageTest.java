package com.ipcs.model;


import com.ipcs.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

public class MessageTest extends DBUnitTest {

    public void testInsertMessage() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria =  session.createCriteria(MessageType.class).add(Restrictions.eq("objectId", 1l));
        MessageType messageType = (MessageType)criteria.list().get(0);
        Message message = new Message.MessageBuilder().withMessageType(messageType).withHeader("TestHeader").withContent("Hello IPCS").withAttachmentAddress("No path").withDate(new Date()).builder();

        session.save(message);
        session.getTransaction().commit();
    }



    public void testInsertMessageWithPerson() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria =  session.createCriteria(MessageType.class).add(Restrictions.eq("objectId", 1l));
        Person person =(Person) session.get(Person.class, 2l);
        MessageType messageType = (MessageType)criteria.list().get(0);
        Message message = new Message.MessageBuilder().withMessageType(messageType).withHeader("TestHeader").withContent("Hello IPCS").withAttachmentAddress("No path").withDate(new Date()).withFromUser(person).withToUser(person).builder();
        session.save(message);
        session.getTransaction().commit();
    }



}  