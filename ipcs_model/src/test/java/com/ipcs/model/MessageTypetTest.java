package com.ipcs.model;

import org.hibernate.Session;
import org.junit.Test;

public class MessageTypetTest extends SpringDBUnit {

    @Test
    public void testInsertSchoolType() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        MessageType messageType = new MessageType();
        messageType.setTypeName("message");
        messageType.setDescription("Plain text");
        session.save(messageType);
        session.getTransaction().commit();
    }
}  