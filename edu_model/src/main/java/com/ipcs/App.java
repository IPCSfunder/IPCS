package com.ipcs;

import org.hibernate.Session;

import com.ipcs.model.Person;
import com.ipcsutil.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
   	 
		session.beginTransaction();
		Person user = new Person();
 
//		user.setUserId(100);
		user.setAccount_name("James2");
		user.setPassword_hash("14147");
//		user.setCreatedDate(new Date());
 
		session.save(user);
		session.getTransaction().commit();
    }
    
    
    
}
