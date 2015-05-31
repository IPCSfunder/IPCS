package com.ipcs.model;


import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/Services.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class ActivityTypetTest {
    @Resource
    SessionFactory sessionFactory;


    @Test
    @DatabaseSetup(value = "/original/activitytype.xml", type = DatabaseOperation.REFRESH)
    @DatabaseTearDown(value = "/original/activitytype.xml", type = DatabaseOperation.DELETE_ALL)
    public void tesqQuerySchoolType() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ActivityType at where at.name='CLASS'");
        ActivityType activityType =  (ActivityType)query.list().get(0);
        session.getTransaction().commit();
        Assert.assertEquals("CLASS", activityType.getName());
        session.close();
    }


}