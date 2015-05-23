package com.ipcs.model;


import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
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
public class SchooTypetTest {
    @Resource
    SessionFactory sessionFactory;

    @Test
    @DatabaseSetup(value = "/original/schoolType.xml", type = DatabaseOperation.REFRESH)
    @ExpectedDatabase(value = "/expected/schoolType.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "/original/schoolType.xml", type = DatabaseOperation.DELETE_ALL)
    public void testInsertSchoolType() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SchoolType schoolType = new SchoolType();
        schoolType.setName("PRIMARY_SCHOOL");
        session.save(schoolType);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @DatabaseSetup(value = "/original/schoolType.xml", type = DatabaseOperation.REFRESH)
    @DatabaseTearDown(value = "/original/schoolType.xml", type = DatabaseOperation.DELETE_ALL)
    public void tesqQuerySchoolType() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SchoolType schoolType = (SchoolType) session.get(SchoolType.class, 1L);
        session.getTransaction().commit();
        Assert.assertEquals("HIGH_SCHOOL", schoolType.getName());
        Assert.assertEquals(2, schoolType.getSchools().size());
        session.close();
    }


    @Test
    @DatabaseSetup(value = "/original/schoolType.xml", type = DatabaseOperation.REFRESH)
    @ExpectedDatabase(value = "/expected/schoolTypeUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "/original/schoolType.xml", type = DatabaseOperation.DELETE_ALL)
    public void testUpdateSchoolType() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SchoolType schoolType = (SchoolType) session.get(SchoolType.class, 1L);
        schoolType.setName("FIRST_PRIMARY_SCHOOL");
        session.update(schoolType);
        session.getTransaction().commit();
        session.close();
    }


    @Test
    @DatabaseSetup(value = "/original/schoolType.xml", type = DatabaseOperation.REFRESH)
    @DatabaseTearDown(value = "/original/schoolType.xml", type = DatabaseOperation.DELETE_ALL)
    public void testDeleteSchoolType() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SchoolType schoolType = (SchoolType) session.createQuery("from SchoolType ST where ST.name='HIGH_SCHOOL'").list().get(0);
        session.delete(schoolType);
        Assert.assertEquals(0,session.createQuery("from SchoolType ST where ST.name='HIGH_SCHOOL'").list().size());
        session.getTransaction().commit();
        session.close();
    }
}