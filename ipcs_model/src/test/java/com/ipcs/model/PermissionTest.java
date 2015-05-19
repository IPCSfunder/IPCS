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
@ContextConfiguration(locations = { "classpath:/Services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
@DatabaseSetup(value="/original/permission.xml", type= DatabaseOperation.REFRESH)
public class PermissionTest
{
	@Resource
	SessionFactory sessionFactory;

	@Test
	@ExpectedDatabase(value= "/expected/permission.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseTearDown(value= "/original/permission.xml",type = DatabaseOperation.DELETE_ALL)
	public void testInsertPermission() {
    	Session session = sessionFactory.openSession();
		session.beginTransaction();
		Role role = (Role)session.get(Role.class,3L);
		Permission permission = new Permission();
		permission.setName("ADD_UPDATE_REMOVE_ACTIVITY");
		permission.addRole(role);
		session.save(permission);
		session.getTransaction().commit();
		session.close();
	}
}
