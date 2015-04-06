/**
 * 
 */
package com.ipcs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.model.School;

/**
 * @author Chen Chao
 *
 */
public class AdminServiceTest {

    AdminService adminService = null;
    List<Person> adminAndStudents = new ArrayList<Person>();
    Person person = null;

    @SuppressWarnings("unchecked")
    @BeforeClass
    public void setUp() {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"Services.xml");
	adminService = (AdminService) appContext
		.getBean("adminServiceImpl");
	
    }

    @Test(groups="inserDummyData")
    public void insertAdmin() {
	Role role = adminService.getRoleByName("admin");	
	School school = adminService.getSchoolByName("PUNGOL");
	Person person = DataFactory.preparePerson("admin", "password");
	person.addRole(role);
	person.addSchool(school);
	adminService.addPerson(person);
	adminAndStudents.add(person);
    }
    
    
    @Test(dependsOnMethods = {"insertAdmin"},groups="inserDummyData")
    public void insertStudents() {
	Role role = adminService.getRoleByName("student");	
	School school = adminService.getSchoolByName("PUNGOL");
	List<Person> persons = DataFactory.prepareStudent(3, "student", "passsword");
	for(Person person: persons){
	    person.addRole(role);
	    person.addSchool(school);
	}
	adminService.addBatchSubodinates(persons);
	adminAndStudents.addAll(persons);
    }
    
    
    
    @Test(dependsOnMethods = {"insertStudents"},groups="inserDummyData")
    public void testFindAllStudents(){
	List<Person> students= adminService.listAllPersonByRoleName("PUNGOL","student");
	Assert.assertEquals(students.size(), 3);
    }
    
    @Test(dependsOnMethods = {"testFindAllStudents"},groups="inserDummyData")
    public void removeDummyData(){
	adminService.deleteBatchSubodinates(adminAndStudents);
    }
    
    
    
//    @Test(groups="inserPersonWithExistingRole")
//    public void testInsertPersonWithExistingRole(){
//	person = new Person();
//	person.setAccount_name("James");
//	person.setPassword_hash("Test");
//	Role role = adminService.getRoleByName("student");
//	person.addRole(role);
//	adminService.addPerson(person);
//    }
//    
//    @Test(dependsOnMethods = {"testInsertPersonWithExistingRole"}, groups="inserPersonWithExistingRole")
//    public void testRemovetPersonWithExistingRole(){
//	adminService.removePerson(person);
//    }
  

}
