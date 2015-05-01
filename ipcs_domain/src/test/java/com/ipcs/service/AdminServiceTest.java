/**
 *
 */
package com.ipcs.service;

import java.util.ArrayList;
import java.util.List;

import com.ipcs.model.Activity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
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

    @Test(groups = "inserDummyData")
    public void insertAdmin() {
        Role role = new Role("admin");
        School school = new School("PUNGOL");
        Person person = DataFactory.preparePerson("admin", "password");
        person.addRole(role);
        person.addSchool(school);
        adminService.addPerson(person);
        adminAndStudents.add(person);
    }


    @Test(dependsOnMethods = {"insertAdmin"}, groups = "inserDummyData")
    public void insertStudents() {
        Role role = adminService.getRoleByName("children");
        School school = adminService.getSchoolByName("PUNGOL");
        List<Person> persons = DataFactory.prepareStudent(3, "children", "passsword");
        for (Person person : persons) {
            person.addRole(role);
            person.addSchool(school);
        }
        adminService.addBatchSubodinates(persons);
        adminAndStudents.addAll(persons);
    }


    @Test(dependsOnMethods = {"insertStudents"}, groups = "inserDummyData")
    public void testFindAllStudents() {
        List<Person> students = adminService.listAllPersonByRoleName("PUNGOL", "children");
        Assert.assertEquals(students.size(), 4);
    }

    @Test(dependsOnMethods = {"testFindAllStudents"}, groups = "inserDummyData")
    public void testGetAdminInfo() {
        Person admin = adminService.getAdminInfo("admin");
        Assert.assertEquals(admin.getSchools().size(), 1);
        Assert.assertEquals(admin.getSchools().iterator().next().getName(), "PUNGOL");
    }


      @Test(groups = "queryActivities")
    public void testQueryActivies() {
        List<Activity> activities = adminService.listAllActivities(2l);
        Assert.assertEquals(activities.size(), 1);
        Assert.assertEquals(activities.iterator().next().getName(), "Math");
    }

    @Test(groups = "listAllChild")
    public void tesListAllChild() {
        List<Person> activities = adminService.listAllChild(4l);
        Assert.assertEquals(activities.size(), 2);
    }



    @AfterClass
    public void tearDown() {
        adminService.deleteBatchSubodinates(adminAndStudents);
    }



}
