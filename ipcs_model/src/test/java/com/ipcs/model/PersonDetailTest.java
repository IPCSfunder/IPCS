package com.ipcs.model;

import org.hibernate.Session;
import org.junit.Test;

import java.util.Date;

public class PersonDetailTest extends SpringDBUnit {

    @Test
    public void testInsertPersonRole() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        PersonDetail personDetail = new PersonDetail.PersonBuilder().withAge(23).withDob(new Date())
                .withFirstName("James").withLastName("Chen").withMarketOption(Boolean.valueOf(false))
                .withNationality("Chinese").withNickName("James").withSex("MALE").build();
        Person person = (Person) session.get(Person.class, 2l);
        personDetail.setPerson(person);
        session.save(personDetail);
        session.getTransaction().commit();
    }
}  