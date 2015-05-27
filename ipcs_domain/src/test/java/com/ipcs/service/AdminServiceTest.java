/**
 *
 */
package com.ipcs.service;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.ipcs.model.Activity;
import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.model.School;
import org.junit.After;
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
import org.testng.Assert;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * @author Chen Chao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/Services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
@DatabaseSetup(value= "/adminService.xml", type= com.github.springtestdbunit.annotation.DatabaseOperation.REFRESH)
public class AdminServiceTest {
    @Resource
    DataSource dataSource;

    AdminService adminService = null;
    Person person = null;


    @Before
    public void setUp() throws Exception{
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "Services.xml");
        adminService = (AdminService) appContext
                .getBean("adminServiceImpl");
    }

    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void insertAdmin() {
        Role role = new Role("ADMIN");
        School school = adminService.getSchoolByName("PUNGOL_PRIMARY_SCHOOL");
        Person person = DataFactory.preparePerson("admin", "password");
        person.addRole(role);
        person.setSchool(school);
        adminService.addPerson(person);

        Person persistedPerson = adminService.getPersonInfo("admin");
        org.junit.Assert.assertEquals(persistedPerson.getPassword_hash(), "password");
        org.junit.Assert.assertEquals( persistedPerson.getSchool().getName(), "PUNGOL_PRIMARY_SCHOOL");
    }


    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void insertStudents() {
        Role role = adminService.getRoleByName("ADMIN");
        School school = adminService.getSchoolByName("PUNGOL_PRIMARY_SCHOOL");
        List<Person> persons = DataFactory.prepareStudent(3, "children", "passsword");
        for (Person person : persons) {
            person.addRole(role);
            person.setSchool(school);
        }
        adminService.addBatchSubodinates(persons);
    }


    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testFindAllStudents() {
        List<Person> admins = adminService.listAllPersonByRoleName("PUNGOL_PRIMARY_SCHOOL", "ADMIN");
        Assert.assertTrue(admins.size() == 2);
    }

    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testGetAdminInfo() {
        Person admin = adminService.getPersonInfo("JamesChen");
        Assert.assertEquals(admin.getSchool().getName(), "PUNGOL_PRIMARY_SCHOOL");
    }


    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testQueryActivies() {
        List<Activity> activities = adminService.listAllActivities(1l);
        Assert.assertEquals(activities.size(), 1);
        Assert.assertEquals(activities.iterator().next().getName(), "Language");
    }

    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testQueryActiviesUnderAdmin() {
        List<Activity> activities = adminService.listAllActivitiesFromAdmin("JamesChen");
        Assert.assertTrue(activities.size() == 2);
    }


    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testQueryChildrenDetail() {
        Person person = adminService.getPersonInfo("JamesChen");
        Assert.assertEquals(person.getPersonDetail().getFirstName(), "Jackson");
    }


    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testUpdatePerson() {
        Person person = adminService.getPersonInfo("JamesChen");
        person.setAccount_name("admin3");
        adminService.updatePerson(person);

        Person updatedPerson = adminService.getPersonInfo("admin3");
        Assert.assertEquals(updatedPerson.getPersonDetail().getFirstName(),"Jackson");
    }


//    @Test
//    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
//    public void tesListAllChild() {
//        List<Person> activities = adminService.listAllChild(4l);
//        Assert.assertEquals(activities.size(), 2);
//    }
//

    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testAddActivityWithHost() {
        School school = adminService.getSchoolByName("PUNGOL_PRIMARY_SCHOOL");
        Person person = adminService.findPersonByName("JamesChen");
        Activity activity = new Activity.ActivityBuilder().withDescription("Physical").withStartDate(new Date()).withLocation("Shanghai").withHost(person).withName("Physical").withSchool(school).builder();
        adminService.addActivity(activity);

        activity = adminService.getActivityDetail(activity.getObjectId());
        Assert.assertEquals(activity.getSchool().getName(),"PUNGOL_PRIMARY_SCHOOL");
    }
}
