package com.ipcs.model;


import org.hibernate.Session;
import org.junit.Test;

public class RoleTest extends SpringDBUnit
{
	
	@Test
	public void testInsertRole() {
    	Session session = sessionFactory.openSession();
  	 
		session.beginTransaction();
		Role role = new Role();
		role.setName("TestStudent2");
		session.save(role);
		session.getTransaction().commit();
	}  

	@Test
	public void testGetRole(){
		Session session = sessionFactory.openSession();
	  	 
		session.beginTransaction();
		Role role = (Role)session.load(Role.class, 5L);
		System.out.println(role.getObjectId());
		session.getTransaction().commit();
	}
}
