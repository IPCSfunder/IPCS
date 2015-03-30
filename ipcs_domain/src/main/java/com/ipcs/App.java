package com.ipcs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ipcs.dao.GenericHibernateDao;
import com.ipcs.model.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    ApplicationContext appContext = 
	  new ClassPathXmlApplicationContext("GenericHiberDao.xml");
    
	GenericHibernateDao<Person, Integer> stockBo = (GenericHibernateDao)appContext.getBean("genericHibernateDao");
	Integer i = null;

	/** insert **/
	Person person = new Person();
	person.setAccount_name("James2");
	person.setPassword_hash("111");
	stockBo.save(person);


	System.out.println("Done");}
}
