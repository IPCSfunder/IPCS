package com.ipcs.model;


import org.hibernate.Session;
import org.junit.Test;

public class ClassTest extends SpringDBUnit {

    @Test
    public void testInsertClass() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Class schooLClass = new Class();
        schooLClass.setName("English");
        Person person = (Person) session.get(Person.class, 2l);
        schooLClass.setTeacher(person);
        Person children = (Person) session.get(Person.class, 3l);
        schooLClass.addChildren(children);
        session.save(schooLClass);
        session.getTransaction().commit();
    }
}  