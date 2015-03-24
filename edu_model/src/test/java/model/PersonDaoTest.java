package model;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.junit.Test;

import com.online_edu.model.Person;
import com.online_edu.model.Role;
import com.online_edu.util.HibernateUtil;

public class PersonDaoTest extends DBTestCase {  
	
    public PersonDaoTest() {  
              System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");  
              System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://PC201311192237.lan:3306/IPCS");  
              System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "ipcs_user");  
              System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,  "");  
    }  

    @Override  
    protected DatabaseOperation getSetUpOperation() throws Exception {  
              return DatabaseOperation.DELETE_ALL;  
    }  

    @Override  
    protected DatabaseOperation getTearDownOperation() throws Exception {  
              return DatabaseOperation.CLEAN_INSERT;  
    }  

    protected void setUp() throws Exception {  
              super.setUp();  
    }  

    @Test
    public void testInsertPersonRole() {
		Session session = HibernateUtil.getSessionFactory().openSession();
 
		session.beginTransaction();
		Role role = new Role("Merchant4");
		Person person = new Person("James4","111");
		person.addRole(role);
		
//		session.save(role);
		session.save(person);
		session.getTransaction().commit();
	}  


 
    @Override  
    protected IDataSet getDataSet() throws Exception {  
              return new FlatXmlDataSetBuilder().build(new FileInputStream(  
                                "src/test/resource/dataset.xml"));
    }  
}  