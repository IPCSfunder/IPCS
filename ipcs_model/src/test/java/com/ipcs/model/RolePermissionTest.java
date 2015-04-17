package com.ipcs.model;


import org.hibernate.Session;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class RolePermissionTest extends SpringDBUnit
{
	
	@Test
	public void testInsertRole() {
    	Session session = sessionFactory.openSession();
  	 
		session.beginTransaction();
		Role role = new Role();
		role.setName("TestStudent");
		Permission permission = new Permission();
		permission.setName("login");
		role.addPermission(permission);
		session.save(permission);
		session.save(role);
		session.getTransaction().commit();
	}  
	

}
