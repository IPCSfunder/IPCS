package com.ipcs.model;


import org.hibernate.Session;
import org.junit.Test;

public class RelationshipTypetTest extends SpringDBUnit{

	@Test
	public void testInsertSchoolType() {	
	Session session = sessionFactory.openSession();

	session.beginTransaction();
	RelationshipType relationshipType = new RelationshipType();
		relationshipType.setName("Parent");
	session.save(relationshipType);
	session.getTransaction().commit();}  
}  