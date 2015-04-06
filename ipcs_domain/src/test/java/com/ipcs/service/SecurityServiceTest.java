/**
 * 
 */
package com.ipcs.service;

import java.util.ArrayList;
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
import com.ipcs.model.School;

/**
 * @author Chen Chao
 *
 */
public class SecurityServiceTest {


    SecurityService securityService = null;
    AdminService adminService = null;
    List<Person> adminAndStudents = new ArrayList<Person>();

    @SuppressWarnings("unchecked")
    @BeforeClass
    public void setUp() {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"Services.xml");
	securityService = (SecurityService) appContext
		.getBean("securityServiceImpl");
	adminService = (AdminService) appContext
		.getBean("adminServiceImpl");
    }

    @Test
    public void insertAdmin() {
	Role role = adminService.getRoleByName("admin");	
	School school = adminService.getSchoolByName("PUNGOL");
	Person person = DataFactory.preparePerson("admin", "password");
	person.addRole(role);
	person.addSchool(school);
	adminService.addPerson(person);
	adminAndStudents.add(person);
    }
    
    @Test(dependsOnMethods = {"insertAdmin"})
    public void testAuthenticateLoginInfo(){
	boolean flag= securityService.authenticateLoginInfo("admin","password");
	Assert.assertTrue(flag);
    }
    
    @Test(enabled=true, dependsOnMethods = {"testAuthenticateLoginInfo"})
    public void testListPermission(){
	List<Permission> permissions= securityService.listPermission("admin");
	Assert.assertEquals(permissions.size(), 3);
    }
    
    
    @Test(dependsOnMethods = {"testListPermission"})
    public void testListRoles(){
	List<Role> roles= securityService.listRole("admin");
	Assert.assertEquals(roles.size(), 1);
	Assert.assertEquals(roles.get(0).getName(), "admin");
    }
    
    @AfterClass
    public void tearDown(){
	adminService.deleteBatchSubodinates(adminAndStudents);
    }


}
