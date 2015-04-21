package com.ipcs.model;


import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class ContactTest extends SpringDBUnit {

    @Test
    public void  testOrder(){
        testInsertContact();
        testQueryContact();
        testDelteContact();
    }



    public void testInsertContact() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Contact contact = new Contact("Shenzhen", "186232", "test@email.com");
        Person person = (Person) session.get(Person.class, 1l);
        contact.setPerson(person);
        session.save(contact);
        session.getTransaction().commit();
    }

    public void testQueryContact(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query cr = session.createQuery("from Contact where mobileNumber = '186232'");
        Contact contact = (Contact)cr.list().get(0);
        session.getTransaction().commit();
        Assert.assertEquals(contact.getPerson().getAccount_name(), "Person");

    }

    public void testDelteContact(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("from Contact where mobileNumber = '186232'");
        Contact contact = (Contact)cr.list().get(0);
        session.delete(contact);
        session.getTransaction().commit();

    }


}  