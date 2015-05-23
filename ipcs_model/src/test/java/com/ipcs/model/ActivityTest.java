package com.ipcs.model;


import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
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
@DatabaseSetup(value="/original/activity.xml", type=DatabaseOperation.REFRESH)
public class ActivityTest{
    @Resource
    SessionFactory sessionFactory;

    @Test
    @ExpectedDatabase(value= "/expected/activity.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value= "/original/activity.xml",type = DatabaseOperation.DELETE_ALL)
    public void testInsertActivity() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2015-05-12 05:00:00");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Activity activity = new Activity.ActivityBuilder().withName("Language").withDescription("Language").withLocation("PUNGGOL").withStartDate(date).builder();
        Person williamWang = (Person) session.get(Person.class, 2l);
        Person michaelMiao = (Person) session.get(Person.class, 4l);
        Person host = (Person) session.get(Person.class, 1l);
        activity.addPerson(williamWang);
        activity.addPerson(michaelMiao);
        School school = (School)session.get(School.class, 1l);
        activity.setSchool(school);
        activity.setHost(host);
        session.save(activity);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @ExpectedDatabase(value= "/expected/activityUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value= "/original/activity.xml",type = DatabaseOperation.DELETE_ALL)
    public void testUpdateActivity() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("from Activity where name = 'Math' ");
        Activity activity = (Activity)cr.list().get(0);
        activity.setName("Chemical");
        Person host = (Person) session.get(Person.class, 4l);
        Person williamWang = (Person) session.get(Person.class, 5l);
        activity.setHost(host);
        activity.addPerson(williamWang);
        session.update(activity);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @DatabaseTearDown(value= "/original/activity.xml",type = DatabaseOperation.DELETE_ALL)
    public void testQueryActivity() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("select p from Activity a inner join a.host p where p.account_name = 'WilliamWang' ");
        Assert.assertEquals(1, cr.list().size());
        Assert.assertEquals("WilliamWang",((Person)cr.list().get(0)).getAccount_name());
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @DatabaseTearDown(value= "/original/activity.xml",type = DatabaseOperation.DELETE_ALL)
    public void testDelete(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query cr = session.createQuery("select a from Activity a where a.name = 'Math' ");
        Activity activity = (Activity)cr.list().get(0);
        session.delete(activity);
        Assert.assertEquals(0,cr.list().size());
        session.getTransaction().commit();
        session.close();
    }
}  