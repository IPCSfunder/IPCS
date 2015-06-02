package com.ipcs.service;


import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.ipcs.model.Permission;
import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.model.School;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/Services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
@DatabaseSetup(value= "/secuirtyService.xml", type=DatabaseOperation.REFRESH)
public class SecurityServiceTest {
    @Resource
    DataSource dataSource;

    SecurityService securityService = null;
    PersonService personService;
    SchoolService schoolService;
    RoleService roleService;

    @Before
    public void purgeData() throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "Services.xml");
        securityService = (SecurityService) appContext
                .getBean("securityServiceImpl");

        personService = (PersonService) appContext
                .getBean("personServiceImpl");
        schoolService = (SchoolService) appContext.getBean("schoolServiceImpl");
        roleService = (RoleService) appContext.getBean("roleServiceImpl");
    }


    @Test
    @DatabaseTearDown(value= "/secuirtyService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void insertAdmin() {
        Role role = roleService.getRoleByName("ADMIN");
        School school = schoolService.getSchoolByName("PUNGOL_PRIMARY_SCHOOL");
        Person person = DataFactory.preparePerson("admin3", "password");
        person.addRole(role);
        person.setSchool(school);
        personService.addPerson(person);

        Person persistedPerson = personService.getPersonDetail("admin3");
        Assert.assertEquals(persistedPerson.getPassword_hash(),"password");
        Assert.assertEquals(((Role)persistedPerson.getRoles().get(0)).getName(),"ADMIN");
    }

    @Test
    @DatabaseTearDown(value= "/secuirtyService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testAuthenticateLoginInfo() {
        boolean flag = securityService.authenticateLoginInfo("JamesChen", "12345");
        Assert.assertTrue(flag);
    }

    @Test
    @DatabaseTearDown(value= "/secuirtyService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testListPermission() {
        List<Permission> permissions = securityService.listPermissionsByUserName("JamesChen");
        Assert.assertEquals(permissions.size(), 3);
    }


    @Test
    @DatabaseTearDown(value= "/secuirtyService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testListRoles() {
        List<Role> roles = securityService.listRolesByUserName("JamesChen");
        Assert.assertEquals(roles.size(), 1);
        Assert.assertEquals(roles.get(0).getName(), "ADMIN");
    }

}  