package com.ipcs.model;


import org.hibernate.Session;
import org.junit.Test;

public class RelationshipTest extends SpringDBUnit{



	@Test
	public void testInsertRelationshipWithPerson() {

		Session session = sessionFactory.openSession();

		session.beginTransaction();
		Relationship relationship = new Relationship();
		RelationshipType relationshipType = (RelationshipType)session.get(RelationshipType.class,1l);
		relationship.setType(relationshipType);
		Person whose = (Person)session.get(Person.class,2l);
		Person iswho = (Person)session.get(Person.class,4l);
		relationship.setWhose(whose);
		relationship.setIswho(iswho);
		session.save(relationship);
		session.getTransaction().commit();
	}
}  