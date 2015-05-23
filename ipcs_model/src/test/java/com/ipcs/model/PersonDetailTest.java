package com.ipcs.model;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/Services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
@DatabaseSetup(value="/original/personDetail.xml", type= DatabaseOperation.REFRESH)
public class PersonDetailTest{
    @Resource
    SessionFactory sessionFactory;

    @Test
    @ExpectedDatabase(value= "/expected/personDetail.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value= "/original/personDetail.xml",type = DatabaseOperation.DELETE_ALL)
    public void testInsertPersonDetails() throws ParseException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PersonDetail personDetail = new PersonDetail.PersonBuilder().withAge(25).withDob(sdf.parse("1986-08-25"))
                .withFirstName("Richard").withLastName("Gard").withMarketOption(Boolean.valueOf(false))
                .withNationality("Singaporean").withNickName("GG").withNric("1234567").withSex("FEMALE").build();
        session.save(personDetail);
        session.getTransaction().commit();
        session.close();
    }
}