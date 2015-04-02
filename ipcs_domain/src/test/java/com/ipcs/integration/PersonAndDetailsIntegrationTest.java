package com.ipcs.integration;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.hibernate.Session;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ipcs.model.Person;
import com.ipcs.model.PersonDetail;
import com.ipcs.model.PersonDetail.Sex;


public class PersonAndDetailsIntegrationTest extends BaseTest {
	Person person = null;
	
	@DataProvider
    public Object[][] providerPerson(){
        Object[][] result = new Object[][] {{"PersonAndDetailsTest","222", "James","Chen",Sex.MALE}};
        return result;
    }
	
	@Test(dataProvider = "providerPerson")
	public void insertPersonWithDetials(String username, String password, String firstName, String lastName, Sex sex) throws ParseException{
		Session session = getCurrentSession();
		session.beginTransaction();
		person = new Person();
		person.setAccount_name(username);
		person.setPassword_hash(password);
		PersonDetail personDetail = 
				new PersonDetail.PersonBuilder().withAge(21)
				.withDob(new SimpleDateFormat("YYYY-mm-dd").parse("1986-07-15")).withFirstName(firstName)
				.withLastName(lastName).withSex(sex).build();
		person.setPersonDetail(personDetail);		
		personDao.save(session,person);
	}
	
	
	
	
	
	@Test(dependsOnMethods = { "insertPersonWithDetials" },dataProvider = "providerPerson")
	public void loadPerson(String username, String password, String firstName, String lastName, Sex sex) { 	
		Session session = getCurrentSession();
		session.beginTransaction();
		Person loadedPerson = personDao.load(session,person.getObjectId());
		
		Assert.assertEquals(username, loadedPerson.getAccount_name());
		Assert.assertEquals(password, loadedPerson.getPassword_hash());
		Assert.assertEquals(firstName, loadedPerson.getPersonDetail().getFist_name());
		Assert.assertEquals(lastName, loadedPerson.getPersonDetail().getLast_name());
		Assert.assertEquals(sex, loadedPerson.getPersonDetail().getSex());
	}
	
	@Test(dependsOnMethods = { "loadPerson" })
	public void deletePerson(){
		Session session = getCurrentSession();
		session.beginTransaction();
		personDao.delete(session,person);			
		session.getTransaction().commit();
	}
}
