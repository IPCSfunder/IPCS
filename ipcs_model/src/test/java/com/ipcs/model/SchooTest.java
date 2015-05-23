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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/Services.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class SchooTest{
	@Resource
	SessionFactory sessionFactory;


	@Test
	@DatabaseSetup(value = "/original/school.xml", type = DatabaseOperation.REFRESH)
	@ExpectedDatabase(value = "/expected/school.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseTearDown(value = "/original/school.xml", type = DatabaseOperation.DELETE_ALL)
	public void testInsertSchool() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		School school = new School();
		school.setAddress("SENGKANG");
		school.setName("PUNGOL_HIGH_SCHOOL");
		SchoolType schoolType = (SchoolType)session.get(SchoolType.class,1L);
		school.setType(schoolType);
		session.save(school);
		session.getTransaction().commit();
        session.close();
}
}