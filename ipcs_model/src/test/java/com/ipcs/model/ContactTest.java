package com.ipcs.model;



import org.hibernate.Session;
import org.junit.Test;

public class ContactTest extends  SpringDBUnit{

    @Test
    public void testInsertPersonRole() {
	Session session =sessionFactory.openSession();
    session.beginTransaction();
	Contact contact = new Contact("Shenzhen","186232","test@email.com");
	session.save(contact);
	session.getTransaction().commit();}

}  