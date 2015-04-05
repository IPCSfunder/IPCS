/**
 * 
 */
package com.ipcs.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ipcs.model.Person;

/**
 * @author Chen Chao
 *
 */
public class AdminServiceTest {

    AdminService<Person> adminService = null;
    List<Person> adminAndStudents = null;

    @SuppressWarnings("unchecked")
    @BeforeTest
    public void setUp() {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"Services.xml");
	adminService = (AdminService<Person>) appContext
		.getBean("adminServiceImpl");
	
    }

    @Test
    public void insertAdmin() {
	adminAndStudents = DummyDataFactory.getAdminInstance();
	adminService.addBatchSubodinates(adminAndStudents);
    }
    
    @Test(dependsOnMethods = {"insertAdmin"})
    public void testFindAllStudents(){
	List<Person> students= adminService.listAllStudents("Pungol Primary School");
	Assert.assertEquals(students.size(), 3);
    }
    
    @AfterTest
    public void tearDown(){
	adminService.deleteBatchSubodinates(adminAndStudents);
    }

}
