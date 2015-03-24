package model;

import org.hibernate.Session;
import org.junit.Test;

import com.online_edu.model.Role;
import com.online_edu.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class RoleTest 
{
	
	@Test
    public void saveRole()
    {
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
