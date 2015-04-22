package com.ipcs.model;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserGroupTest extends SpringDBUnit{


	@Test
	public void testOrder(){
		testInsertUserGroup();
		testUpdateUserGroup();
		testQueryUserGroupy();
		testDelete();
	}


	public void testInsertUserGroup() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Person teacher = (Person) session.get(Person.class, 3l);
		Person child = (Person) session.get(Person.class, 2l);
		Person parent = (Person) session.get(Person.class, 4l);
		UserGroup userGroup = new UserGroup();
		userGroup.setName("Group1");
		userGroup.setOrginizer(teacher);
		userGroup.setGroupMember(child);

		UserGroup userGroup2 = new UserGroup();
		userGroup2.setName("Group1");
		userGroup2.setOrginizer(teacher);
		userGroup2.setGroupMember(parent);
		session.save(userGroup);
		session.save(userGroup2);
		session.getTransaction().commit();


	}


	public void testUpdateUserGroup() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query cr = session.createQuery("from UserGroup u inner join fetch u.orginizer p where p.account_name = 'teacher' ");
		UserGroup group = (UserGroup)cr.list().get(0);
		group.setName("Group2");
		Person admin = (Person) session.get(Person.class, 1l);
		group.setGroupMember(admin);
		session.update(group);
		session.getTransaction().commit();
	}


	public void testQueryUserGroupy() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query cr = session.createQuery("select u from UserGroup u inner join fetch u.orginizer p where p.account_name = 'teacher'");
		List<UserGroup> userGroups = cr.list();
		Assert.assertEquals(2,userGroups.size());
	}

	public void testDelete(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query cr = session.createQuery("from UserGroup where name = 'Group2'");
		for(UserGroup userGroup:(List<UserGroup>)cr.list()){
			session.delete(userGroup);
		}
		session.getTransaction().commit();
	}
}  