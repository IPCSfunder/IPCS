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
@DatabaseSetup(value= "/adminService.xml", type= DatabaseOperation.REFRESH)
public class ActivityServiceTest {
    @Resource
    DataSource dataSource;

    ActivityService activityService = null;
    PersonService personService;
    SchoolService schoolService;
    Person person = null;


    @Before
    public void setUp() throws Exception{
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "Services.xml");
        activityService = (ActivityService) appContext
                .getBean("activityServiceImpl");
        personService = (PersonService) appContext
                .getBean("personServiceImpl");
        schoolService = (SchoolService) appContext.getBean("schoolServiceImpl");

    }


    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testQueryActivies() {
        List<Activity> activities = activityService.listActivitiesByChildId(1l);
        Assert.assertEquals(activities.size(), 1);
        Assert.assertEquals(activities.get(0).getName(), "Language");
    }

    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testGetActivityInfo() {
        Activity activitie = activityService.getActivityDetail(2L);
        Assert.assertEquals(activitie.getName(), "Language");
        Assert.assertEquals(activitie.getSchool().getName(), "PUNGOL_PRIMARY_SCHOOL");
    }

    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testQueryActiviesUnderAdmin() {
        List<Activity> activities = activityService.listActivitiesByAdminName("JamesChen");
        Assert.assertEquals(activities.size(),3);
    }


    @Test
    @DatabaseTearDown(value= "/adminService.xml",type = DatabaseOperation.CLEAN_INSERT)
    public void testAddActivityWithHost() {
        School school = schoolService.getSchoolByName("PUNGOL_PRIMARY_SCHOOL");
        Person person = personService.findPersonByName("JamesChen");
        Activity activity = new Activity.ActivityBuilder().withDescription("Physical").withStartTime(new Date()).withLocation("Shanghai").withHost(person).withName("Physical").withSchool(school).builder();
        activityService.addActivity(activity);

        activity = activityService.getActivityDetail(activity.getObjectId());
        Assert.assertEquals(activity.getSchool().getName(),"PUNGOL_PRIMARY_SCHOOL");
    }

}
