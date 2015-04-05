/**
 * 
 */
package com.ipcs.service;

import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ipcs.model.Permission;
import com.ipcs.model.Person;
import com.ipcs.model.Role;

/**
 * @author Chen Chao
 *
 */
public class SecurityServiceTest {


    SecurityService securityService = null;
    AdminService<Person> adminService = null;
    List<Person> adminAndStudents = null;

    @SuppressWarnings("unchecked")
    @BeforeClass
    public void setUp() {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"Services.xml");
	securityService = (SecurityService) appContext
		.getBean("securityServiceImpl");
	adminService = (AdminService<Person>) appContext
		.getBean("adminServiceImpl");
    }

    @Test
    public void insertAdmin() {
	adminAndStudents = new DummyDataFactory().getAdminInstance();
	adminService.addBatchSubodinates(adminAndStudents);
    }
    
    @Test(dependsOnMethods = {"insertAdmin"})
    public void testAuthenticateLoginInfo(){
	boolean flag= securityService.authenticateLoginInfo("DummyAdmin","Test");
	Assert.assertTrue(flag);
    }
    
    @Test(enabled=true, dependsOnMethods = {"testAuthenticateLoginInfo"})
    public void testListPermission(){
	List<Permission> permissions= securityService.listPermission("DummyAdmin");
	Assert.assertEquals(permissions.size(), 2);
    }
    
    
    @Test(dependsOnMethods = {"testListPermission"})
    public void testListRoles(){
	List<Role> roles= securityService.listRole("DummyAdmin");
	Assert.assertEquals(roles.size(), 1);
	Assert.assertEquals(roles.get(0).getName(), "Admin");
    }
    
    @AfterClass
    public void tearDown(){
	adminService.deleteBatchSubodinates(adminAndStudents);
    }


}
