package com.ipcs.integration;

import junit.framework.Assert;

import org.hibernate.Session;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ipcs.model.Person;
import com.ipcs.model.Role;


public class RoleIntegrationTest extends BaseTest {
	
	@DataProvider
    public Object[][] providerPerson(){
        Object[][] result = new Object[][] {{"Admin"}};
        return result;
    }
	
	@Test(dataProvider = "providerPerson")
	public void insertSinglePerson(String roleName){
		Session session = getCurrentSession();
		session.beginTransaction();
		Role role = new Role.RoleBuilder().withName(roleName).build();
//		personDao.save(session,role);
		session.getTransaction().commit();
	}
	
	@Test(dependsOnMethods = { "insertSinglePerson" },dataProvider = "providerPerson")
	public void loadPerson(String username, String password) { 	
		Session session = getCurrentSession();
		session.beginTransaction();
//		Role role = personDao.load(session,person.getObjectId());
//		Assert.assertEquals(username, person.getAccount_name());
//		Assert.assertEquals(password, person.getPassword_hash());
	}
	
	@Test(dependsOnMethods = { "loadPerson" })
	public void deletePerson(){
		Session session = getCurrentSession();
		session.beginTransaction();
//		personDao.delete(session,person);	
		
		session.getTransaction().commit();
	}
}
