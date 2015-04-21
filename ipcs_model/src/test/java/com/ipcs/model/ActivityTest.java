package com.ipcs.model;


import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class ActivityTest extends SpringDBUnit {

    @Test
    public void testOrder(){
        testInsertActivity();
        testUpdateActivity();
        testQueryActivity();
        testDelete();
    }


    public void testInsertActivity() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Person person = (Person) session.get(Person.class, 3l);
        Activity activity = new Activity.ActivityBuilder().withName("Language").withDescription("Language").withHost(person).withLocation("PUNGGOL").withStartDate(new Date()).builder();
        Person child = (Person) session.get(Person.class, 2l);
        activity.addPerson(child);
        session.save(activity);
        session.getTransaction().commit();


    }


    public void testUpdateActivity() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("from Activity where name = 'Language' ");
        Activity activity = (Activity)cr.list().get(0);
        activity.setName("Chemical");
        Person admin = (Person) session.get(Person.class, 1l);
        activity.setHost(admin);
        HashSet<Person> persons = new HashSet<Person>();
        persons.add(admin);
        activity.setPersons(persons);
        session.update(activity);
        session.getTransaction().commit();
    }


    public void testQueryActivity() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("select p from Activity a left join a.persons p where a.name = 'Chemical' ");
        Person person = (Person)cr.list().get(0);
        session.getTransaction().commit();
        Assert.assertEquals("Person", person.getAccount_name());
    }

    public void testDelete(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("select a from Activity a where a.name = 'Chemical' ");
        Activity activity = (Activity)cr.list().get(0);
        session.delete(activity);
        session.getTransaction().commit();
    }
}  