package com.ipcs.model;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcs.model.Role;
import com.ipcs.util.HibernateUtil;

public abstract class DBUnitTest extends DBTestCase{
  
	
    public DBUnitTest() {  
              System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");  
              System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://120.24.166.170:3306/ipcs");
              System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "ipcs_admin");
              System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,  "password");
    }  

    @Override  
    protected DatabaseOperation getSetUpOperation() throws Exception {  
              return DatabaseOperation.REFRESH;  
    }  

    @Override  
    protected DatabaseOperation getTearDownOperation() throws Exception {  
              return DatabaseOperation.CLEAN_INSERT;  
    }  

    protected void setUp() throws Exception {  
              super.setUp();  
    }  



 
    @Override  
    protected IDataSet getDataSet() throws Exception {  
              return new FlatXmlDataSetBuilder().build(new FileInputStream(  
                                "src/test/resource/dataset.xml"));
    }  

}
