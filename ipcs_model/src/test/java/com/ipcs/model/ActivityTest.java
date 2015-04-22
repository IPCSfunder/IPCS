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
        Person host = (Person) session.get(Person.class, 3l);
        Activity activity = new Activity.ActivityBuilder().withName("Language").withDescription("Language").withHost(host).withLocation("PUNGGOL").withStartDate(new Date()).builder();
        Person child = (Person) session.get(Person.class, 2l);
        Person parent = (Person) session.get(Person.class, 4l);
        activity.addPerson(child);
        activity.addPerson(parent);
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
        activity.addPerson(admin);
        session.update(activity);
        session.getTransaction().commit();
    }


    public void testQueryActivity() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("select p from Activity a inner join a.persons p where a.name = 'Chemical' ");
        Assert.assertEquals(3, cr.list().size());
        Query cr2 = session.createQuery("select p from Activity a inner join a.host h inner join a.persons p where h.account_name = 'admin' ");
        Assert.assertEquals(3, cr.list().size());
        session.getTransaction().commit();
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