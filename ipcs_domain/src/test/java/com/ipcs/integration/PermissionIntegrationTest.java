package com.ipcs.integration;

import junit.framework.Assert;

import org.hibernate.Session;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ipcs.model.Person;

public class PermissionIntegrationTest extends BaseTest {
    Person person = null;

    @DataProvider
    public Object[][] providerPerson() {
	Object[][] result = new Object[][] { { "MyFirstTest", "111" } };
	return result;
    }

    @Test(dataProvider = "providerPerson")
    public void insertSinglePerson(String username, String password) {
	person = new Person();
	person.setAccount_name(username);
	person.setPassword_hash(password);
	personDao.save(person);
    }

    @Test(dependsOnMethods = { "insertSinglePerson" }, dataProvider = "providerPerson")
    public void loadPerson(String username, String password) {
	personDao.load(person.getObjectId());
	Assert.assertEquals(username, person.getAccount_name());
	Assert.assertEquals(password, person.getPassword_hash());
    }

    @Test(dependsOnMethods = { "loadPerson" })
    public void deletePerson() {
	personDao.delete(person);
    }
}
