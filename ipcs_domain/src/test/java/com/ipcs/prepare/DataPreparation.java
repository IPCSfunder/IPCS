package com.ipcs.prepare;


import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/Services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
public class DataPreparation {
    @Resource
    DataSource dataSource;

    @Before
    public void purgeData() throws Exception {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("delete from person_role;");
        statement.execute("delete from person_school;");
        statement.execute("delete from role_permission;");
        statement.execute("delete from role;");
        statement.execute("delete from schedule;");
        statement.execute("delete from permission;");
        statement.execute("delete from school;");
        statement.execute("delete from school_type;");
        statement.execute("delete from activity;");
        statement.execute("delete from contact;");
        statement.execute("delete from message;");
        statement.execute("delete from message_type;");
        statement.execute("delete from relationship;");
        statement.execute("delete from relationship_type;");
        statement.execute("delete from user_group;");
        statement.execute("delete from person;");
        statement.execute("delete from person_detail;");
        connection.close();
    }

    @Test
    @DatabaseSetup(value= "/secuirtyService.xml", type=DatabaseOperation.REFRESH)
    public void prepareData() throws ParseException {
    }
}  