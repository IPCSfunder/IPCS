package com.ipcs.model;


import org.hibernate.Session;

import com.ipcs.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class RolePermissionTest extends DBUnitTest
{
	

	public void testInsertRole() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
  	 
		session.beginTransaction();
		Role role = new Role();
		role.setName("Student");
		Permission permission = new Permission();
		permission.setName("login");
		role.addPermission(permission);
		session.save(permission);
		session.save(role);
		session.getTransaction().commit();
	}  
	

}
