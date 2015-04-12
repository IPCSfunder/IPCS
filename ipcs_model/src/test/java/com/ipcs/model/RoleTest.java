package com.ipcs.model;


import org.hibernate.Session;

import com.ipcs.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class RoleTest extends DBUnitTest
{
	

	public void testInsertRole() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
  	 
		session.beginTransaction();
		Role role = new Role();
		role.setName("TestStudent2");
		session.save(role);
		session.getTransaction().commit();
	}  
	
	public void testGetRole(){
		Session session = HibernateUtil.getSessionFactory().openSession();
	  	 
		session.beginTransaction();
		Role role = (Role)session.load(Role.class, 5L);
		System.out.println(role.getObjectId());
		session.getTransaction().commit();
	}
}
