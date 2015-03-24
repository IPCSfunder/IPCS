package com.ipcs.model;


import org.hibernate.Session;

import com.ipcs.model.Role;
import com.ipcsutil.HibernateUtil;

/**
 * Hello world!
 *
 */
public class RoleTest extends DBUnitTest
{
	

	public void testInsertPerson() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
  	 
		session.beginTransaction();
		Role role = new Role();

//		user.setUserId(100);
		role.setName("Student");
//		user.setCreatedDate(new Date());

		session.save(role);
		session.getTransaction().commit();
	}  


}
