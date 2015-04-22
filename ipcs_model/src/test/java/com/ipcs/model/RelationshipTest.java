package com.ipcs.model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class RelationshipTest extends SpringDBUnit{

	@Test
	public void testOrder(){
		testInsertRelationshipWithPerson();
		testQueryRelationshipWithPerson();
	}
	public void testInsertRelationshipWithPerson() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Relationship relationship = new Relationship();
		RelationshipType relationshipType = (RelationshipType)session.get(RelationshipType.class,1l);
		relationship.setType(relationshipType);
		Person whose = (Person)session.get(Person.class,1l);
		Person isWho = (Person)session.get(Person.class,2l);
		relationship.setWhose(whose);
		relationship.setIswho(isWho);
		session.save(relationship);
		session.getTransaction().commit();
	}

	public void testQueryRelationshipWithPerson(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query cr = session.createQuery("select i from Relationship r inner join r.whose p inner join r.iswho i where p.account_name = 'Person' ");
		Assert.assertEquals("Child", ((Person) cr.list().get(0)).getAccount_name());
		session.getTransaction().commit();
	}


}  