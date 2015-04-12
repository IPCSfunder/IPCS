package com.ipcs.model;


import com.ipcs.util.HibernateUtil;
import org.hibernate.Session;

public class MessageTypetTest extends DBUnitTest {

    public void testInsertSchoolType() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        MessageType messageType = new MessageType();
        messageType.setTypeName("message");
        messageType.setDescription("Plain text");
        session.save(messageType);
        session.getTransaction().commit();
    }
}  